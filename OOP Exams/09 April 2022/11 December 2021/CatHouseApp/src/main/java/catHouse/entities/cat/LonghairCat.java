package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int KILOGRAMS = 9;
    private static final int KILOGRAMS_TO_ADD = 3;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        this.setKilograms(KILOGRAMS + KILOGRAMS_TO_ADD);
    }
}
