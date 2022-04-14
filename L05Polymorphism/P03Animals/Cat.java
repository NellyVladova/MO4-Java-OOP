package L05Polymorphism.P03Animals;

public class Cat extends Animal{
    private String name;
    private String favouriteFood;

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    public String getName() {
        return name;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    @Override
    public String explainSelf() {
        return String.format("%s%nMEEOW", super.explainSelf());
    }
}
