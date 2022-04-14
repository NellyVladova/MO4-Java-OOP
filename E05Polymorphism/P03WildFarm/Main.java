package E05Polymorphism.P03WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Animal> animalList = new ArrayList<>();
        while (!input.equals("End")) {
            String[] inputParts = input.split("\\s+");
            Animal animal = createAnimal(inputParts);

            String[] inputFood = scanner.nextLine().split("\\s+");
            Food food = createFood(inputFood);

            animal.makeSound();
            animal.eat(food);

            animalList.add(animal);
            input = scanner.nextLine();
        }

        animalList.forEach(System.out::println);
    }

    private static Food createFood(String[] inputFood) {
        String foodType = inputFood[0];
        int foodQuantity = Integer.parseInt(inputFood[1]);

        if(foodType.equals("Meat")){
            return new Meat(foodQuantity);
        }else return new Vegetable(foodQuantity);
    }

    private static Animal createAnimal(String[] input) {
        String animalType = input[0];
        String animalName = input[1];
        double animalWeight = Double.parseDouble(input[2]);
        String leavingRegion = input[3];
        switch (animalType){
            case "Cat":
                String breed = input[4];
                return new Cat(animalName, animalType, animalWeight, leavingRegion, breed);
            case "Mouse":
                return new Mouse(animalName, animalType, animalWeight, leavingRegion);
            case "Tiger":
                return new Tiger(animalName, animalType, animalWeight, leavingRegion);
            case "Zebra":
                return new Zebra(animalName, animalType, animalWeight, leavingRegion);
            default:
                throw new IllegalArgumentException("No such animal");
        }
    }
}
