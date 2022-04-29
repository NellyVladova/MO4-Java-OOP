package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument{
    private static final int ZERO_CONSTANT = 0;
    private static final int DECREASE_POWER = 10;

    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        this.power = Math.max(ZERO_CONSTANT, (this.getPower() - DECREASE_POWER));
    }

    @Override
    public boolean isBroken() {
        return this.power == ZERO_CONSTANT;
    }

    private void setPower(int power) {
        if (power < ZERO_CONSTANT){
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }
}