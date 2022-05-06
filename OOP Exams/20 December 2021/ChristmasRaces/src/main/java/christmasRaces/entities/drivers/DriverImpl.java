package christmasRaces.entities.drivers;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver {
    private static final int NAME_LENGTH = 5;

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        }
        this.setCar(car);
        this.canParticipate = true;

    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setCar(Car car) {
        this.car = car;
    }

    private void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }
}
