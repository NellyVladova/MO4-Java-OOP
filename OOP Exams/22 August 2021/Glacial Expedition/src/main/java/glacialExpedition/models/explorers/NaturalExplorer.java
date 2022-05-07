package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double ENERGY_UNITS = 60;
    private static final double DECREASED_UNITS = 7;
    private static final double ZERO_CONSTANT = 0;

    public NaturalExplorer(String name) {
        super(name, ENERGY_UNITS);
    }

    @Override
    public void search() {
        if (getEnergy() <= DECREASED_UNITS) {
            super.setEnergy(ZERO_CONSTANT);
        } else {
            super.setEnergy(this.getEnergy() - DECREASED_UNITS);
        }
    }
}
