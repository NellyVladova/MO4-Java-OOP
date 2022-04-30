package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHouse implements House{
    private static final int ZERO_CONSTANT = 0;
    public static final String CAT_HOUSE_TO_STRING = "%s %s:";
    public static final String CATS_NAMES_DELIMITER = " ";
    public static final String TOYS_COUNT_ADN_SOFTNESS_TO_STRING = "Toys: %d Softness: %d";

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    protected BaseHouse(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumSoftness() {
        return this.toys
                .stream()
                .mapToInt(Toy::getSoftness)
                .sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (this.capacity <= this.cats.size()) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        this.cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public void feeding() {
        this.cats.forEach(Cat::eating);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }

    @Override
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();

        stats.append(String.format(CAT_HOUSE_TO_STRING, this.name,
                this.getClass().getSimpleName()))
                .append(System.lineSeparator());

        if (cats.size() == ZERO_CONSTANT) {
            stats.append("Cats: none").append(System.lineSeparator());
        } else {
            stats.append("Cats:");

            for (Cat cat : cats) {
                stats.append(CATS_NAMES_DELIMITER).append(cat.getName());
            }

            stats.append(System.lineSeparator());
        }

        int toysCount = toys.size();
        int sumOfSoftness = toys.stream().mapToInt(Toy::getSoftness).sum();

        stats.append(String.format(TOYS_COUNT_ADN_SOFTNESS_TO_STRING, toysCount, sumOfSoftness))
                .append(System.lineSeparator());

        return stats.toString().trim();
    }
}
