package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class PresentImpl implements Present{
    private static final int ZERO_CONSTANT = 0;
    private static final int DECREASE_ENERGY = 10;

    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.energyRequired == ZERO_CONSTANT;
    }

    @Override
    public void getCrafted() {
        this.energyRequired = Math.max(ZERO_CONSTANT, (this.getEnergyRequired() - DECREASE_ENERGY));
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setEnergyRequired(int energyRequired) {
        if(energyRequired < ZERO_CONSTANT){
            throw new IllegalArgumentException(ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }
}
