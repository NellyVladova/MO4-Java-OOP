package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private static final int KILOGRAMS = 7;
    private static final int KILOGRAMS_TO_ADD = 1;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        this.setKilograms(KILOGRAMS + KILOGRAMS_TO_ADD);
    }
}
