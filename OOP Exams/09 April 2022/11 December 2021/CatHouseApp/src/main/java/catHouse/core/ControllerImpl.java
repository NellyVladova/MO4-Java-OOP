package catHouse.core;


import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.Repository;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;

            case "LongHouse":
                house = new LongHouse(name);
                break;

            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        this.houses.put(house.getName(), house);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();

                break;
            case "Mouse":
                toy = new Mouse();

                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        House house = this.houses.get(houseName);
        house.buyToy(toy);
        this.toys.removeToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        House house = this.houses.get(houseName);
        String houseType = house.getClass().getSimpleName();

        boolean isSuitable = ((houseType.equals("LongHouse")) && (catType.equals("LonghairCat")) ||
                (houseType.equals("ShortHouse")) && (catType.equals("ShorthairCat")));

        if (!isSuitable) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        } else {
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }
    }

    @Override
    public String feedingCat(String houseName) {
        House house = this.houses.get(houseName);
        house.feeding();

        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = this.houses.get(houseName);
        double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double sum = catsPrice + toysPrice;

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();
        this.houses.values()
                .forEach(house -> stats.append(house.getStatistics())
                .append(System.lineSeparator()));

        return stats.toString().trim();
    }
}
