package E05Polymorphism.P03WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{
    private String livingRegion;

    public Mammal(String animalName, String animalType, double animalWeight, String leavingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = leavingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat formatted = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]", getAnimalType(), getAnimalName(),
                formatted.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}
