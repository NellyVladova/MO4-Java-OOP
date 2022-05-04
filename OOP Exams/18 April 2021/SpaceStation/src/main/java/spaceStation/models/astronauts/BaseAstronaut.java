package spaceStation.models.astronauts;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

public abstract class BaseAstronaut implements Astronaut {
    private static final int DECREASED_OXYGEN_UNITS = 10;
    private static final int ZERO_CONSTANT = 0;
    private static final String EMPTY_BAG = "none";

    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        bag = new Backpack();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > ZERO_CONSTANT;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        this.oxygen = Math.max(0, this.oxygen - DECREASED_OXYGEN_UNITS);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < ZERO_CONSTANT) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String toString() {
        String bagItems = this.getBag().getItems().size() == 0
                ? EMPTY_BAG
                : String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, this.bag.getItems());
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, this.getName())).append(System.lineSeparator())
                .append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, this.getOxygen())).append(System.lineSeparator())
                .append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, bagItems));

        return builder.toString().trim();
    }
}
