package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private static final int SUITABLE_ENERGY = 50;

    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private Shop shop;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);

        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        Present present = this.presentRepository.findByName(presentName);

        List<Helper> suitableHelpers = this.helperRepository.getModels().stream()
                .filter(helper -> helper.getEnergy() > SUITABLE_ENERGY).collect(Collectors.toList());

        int brokenInstrumentsCount = 0;
        String stateOfPresent = null;

        if (suitableHelpers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }
        if (present != null) {
            for (Helper helper : suitableHelpers) {
                this.shop.craft(present, helper);
                List<Instrument> brokenInstruments = helper.getInstruments().stream().filter(Instrument::isBroken).collect(Collectors.toList());
                brokenInstrumentsCount += brokenInstruments.size();
            }
            if(present.isDone()){
                stateOfPresent = "done";
            }else {
                stateOfPresent = "not done";
            }
        }

        return String.format(ConstantMessages.PRESENT_DONE + ConstantMessages.COUNT_BROKEN_INSTRUMENTS, presentName, stateOfPresent, brokenInstrumentsCount);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        long countCraftedPresents = this.presentRepository.getModels().stream().filter(Present::isDone).count();

        builder.append(String.format("%d presents are done!", countCraftedPresents)).append(System.lineSeparator())
                .append("Helpers info:").append(System.lineSeparator());
        for (Helper helper : helperRepository.getModels()){
            long brokenInstrumentsCount = helper.getInstruments().stream().filter(Instrument::isBroken).count();

            builder.append(String.format("Name: %s", helper.getName())).append(System.lineSeparator())
                    .append(String.format("Energy: %d", helper.getEnergy())).append(System.lineSeparator())
                    .append(String.format("Instruments: %d not broken left", helper.getInstruments().size() - brokenInstrumentsCount)).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
