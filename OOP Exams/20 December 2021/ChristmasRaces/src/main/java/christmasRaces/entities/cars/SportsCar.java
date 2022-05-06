package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar {
    private static final double CUBIC_CENTIMETERS = 3000;
    private static final double MINIMUM_HORSEPOWER = 250;
    private static final double MAXIMUM_HORSEPOWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int horsePower) {
        if (horsePower < MINIMUM_HORSEPOWER || horsePower > MAXIMUM_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
    }
}
