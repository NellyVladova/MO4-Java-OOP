package fairyShop.models;

public class Sleepy extends BaseHelper{
    private static final int ENERGY_UNITS = 50;
    private static final int ZERO_CONSTANT = 0;
    private static final int DECREASE_ENERGY = 5;

    public Sleepy(String name) {
        super(name, ENERGY_UNITS);
    }
    @Override
    public void work() {
        this.setEnergy(Math.max(ZERO_CONSTANT, this.getEnergy() - DECREASE_ENERGY));
    }
}
