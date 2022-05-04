package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final int OXYGEN_UNITS = 50;

    public Geodesist(String name) {
        super(name, OXYGEN_UNITS);
    }
}
