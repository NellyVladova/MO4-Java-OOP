package L05Polymorphism.P03Animals;

public class Dog extends Animal{
    private String name;
    private String favouriteFood;

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFavouriteFood() {
        return favouriteFood;
    }

    @Override
    public String explainSelf() {
        return String.format("%s%nDJAAF", super.explainSelf());
    }
}
