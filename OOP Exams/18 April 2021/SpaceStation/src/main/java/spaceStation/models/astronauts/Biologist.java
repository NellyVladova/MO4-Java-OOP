package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static final int OXYGEN_UNITS = 70;
    private static final int DECREASED_OXYGEN_UNITS = 5;

    public Biologist(String name) {
        super(name, OXYGEN_UNITS);
    }
    @Override
    public void breath() {
        this.setOxygen(Math.max(0, this.getOxygen()) - DECREASED_OXYGEN_UNITS);
    }
}
