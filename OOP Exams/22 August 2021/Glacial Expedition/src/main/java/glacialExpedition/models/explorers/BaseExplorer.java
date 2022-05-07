package glacialExpedition.models.explorers;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

public abstract class BaseExplorer implements Explorer {
    private static final int ZERO_CONSTANT = 0;
    private static final int DECREASED_ENERGY = 15;

    private String name;
    private double energy;
    private Suitcase suitcase;

    protected BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canSearch() {
        return this.energy > ZERO_CONSTANT;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy = Math.max(ZERO_CONSTANT, this.energy - DECREASED_ENERGY);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(double energy) {
        if (energy < ZERO_CONSTANT) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, this.name)).append(System.lineSeparator());
        builder.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, this.energy)).append(System.lineSeparator());
        builder.append("Suitcase exhibits: ");

        int exhibitsCount = this.suitcase.getExhibits().size();

        String suitcaseExhibit = exhibitsCount == 0
                ? "None"
                : String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, this.getSuitcase().getExhibits());

        builder.append(suitcaseExhibit);

        return builder.toString().trim();
    }
}
