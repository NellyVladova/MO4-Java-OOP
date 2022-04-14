package E05Polymorphism.P03WildFarm;

public class Mouse extends Mammal{

    public Mouse(String animalName, String animalType, double animalWeight, String leavingRegion) {
        super(animalName, animalType, animalWeight, leavingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        if (food instanceof Meat){
            System.out.println("Mice are not eating that type of food!");
        }else super.eat(food);
    }
}
