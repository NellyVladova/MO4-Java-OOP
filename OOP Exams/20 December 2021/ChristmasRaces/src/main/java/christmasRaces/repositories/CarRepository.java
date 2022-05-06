package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository implements Repository<Car> {
    private Map<String, Car> models;

    public CarRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        return this.models.get(name);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Car model) {
        this.models.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        return this.models.remove(model.getModel(),model);
    }
}
