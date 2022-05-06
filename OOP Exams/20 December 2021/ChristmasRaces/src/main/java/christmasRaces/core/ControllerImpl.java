package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private static final int MIN_PARTICIPANTS = 3;

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver existingDriver = this.driverRepository.getByName(driver);
        if (existingDriver != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS, driver));
        }
        Driver driverToAdd = new DriverImpl(driver);

        this.driverRepository.add(driverToAdd);
        return String.format(OutputMessages.DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car existingCar = this.carRepository.getByName(model);
        if(existingCar != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }
        Car newCar = null;
        switch (type){
            case "Muscle":
                newCar = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                newCar = new SportsCar(model, horsePower);
                break;
        }
        this.carRepository.add(newCar);

        return String.format(OutputMessages.CAR_CREATED, type, model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = this.driverRepository.getByName(driverName);
        if(driver == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        Car car = this.carRepository.getByName(carModel);
        if(car == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);

        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = this.raceRepository.getByName(raceName);
        if(race == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Driver driver = this.driverRepository.getByName(driverName);
        if(driver == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);

        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Collection<Driver> drivers = race.getDrivers();
        if (drivers.size() < MIN_PARTICIPANTS) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, MIN_PARTICIPANTS));
        }
        int laps = race.getLaps();
        List<Driver> topThreeDrivers = drivers.stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(laps), d1.getCar().calculateRacePoints(laps)))
                .limit(3).collect(Collectors.toList());
        this.raceRepository.remove(race);

        Driver firstDriver = topThreeDrivers.get(0);
        Driver secondDriver = topThreeDrivers.get(1);
        Driver thirdDriver = topThreeDrivers.get(2);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(OutputMessages.DRIVER_FIRST_POSITION, firstDriver, raceName)).append(System.lineSeparator())
                .append(String.format(OutputMessages.DRIVER_SECOND_POSITION, secondDriver, raceName)).append(System.lineSeparator())
                .append(String.format(OutputMessages.DRIVER_THIRD_POSITION, thirdDriver, raceName));

        return builder.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        boolean isExisting =  race.equals(this.raceRepository.getByName(name));
        if(isExisting){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        this.raceRepository.add(race);

        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
