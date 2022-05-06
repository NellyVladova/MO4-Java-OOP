package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceImpl implements Race{
    private static final int NAME_LENGTH = 5;
    private static final int LAPS_COUNT = 1;

    private String name;
    private int laps;
    private Map<String, Driver> drivers;;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers.values();
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }
        if (!driver.getCanParticipate()){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,driver.getName()));
        }
        boolean isExisting = this.drivers.get(driver.getName()) != null;
        if (isExisting){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,driver.getName(),this.getName()));
        }
        this.drivers.put(driver.getName(),driver);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < LAPS_COUNT) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_LAPS);
        }
        this.laps = laps;
    }
}
