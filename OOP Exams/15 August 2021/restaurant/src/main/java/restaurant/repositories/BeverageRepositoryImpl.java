package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private Map<String, Beverages> beveragesMap;

    public BeverageRepositoryImpl() {
        this.beveragesMap = new LinkedHashMap<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return this.beveragesMap
                .values()
                .stream()
                .filter(beverage -> beverage.getName().equals(drinkName) &&
                        beverage.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(this.beveragesMap.values());
    }

    @Override
    public void add(Beverages entity) {
        this.beveragesMap.put(entity.getName(), entity);
    }
}
