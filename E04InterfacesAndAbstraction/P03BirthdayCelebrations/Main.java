package E04InterfacesAndAbstraction.P03BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Birthable> birthableList = new ArrayList<>();
        while (!input.equals("End")){
            String objectType = input.split("\\s+")[0];
            switch (objectType){
                case "Citizen":
                    Citizen citizen = createCitizen(input);
                    birthableList.add(citizen);
                    break;
                case "Pet":
                    Pet pet = createPet(input);
                    birthableList.add(pet);
                    break;
                case "Robot":
                    Robot robot = createRobot(input);
                    break;
            }

            input = scanner.nextLine();
        }

        String specificYear = scanner.nextLine();
        for (Birthable element : birthableList) {
            if (element.getBirthDate().endsWith(specificYear)) {
                System.out.println(element.getBirthDate());
            }
        }
    }

    private static Robot createRobot(String input) {
        String model = input.split("\\s+")[1];
        String robotId = input.split("\\s+")[2];

        return new Robot(robotId, model);
    }

    private static Pet createPet(String input) {
        String petName = input.split("\\s+")[1];
        String petBirthDate = input.split("\\s+")[2];

        return new Pet(petName, petBirthDate);
    }

    private static Citizen createCitizen(String input) {
        String name = input.split("\\s+")[1];
        int age = Integer.parseInt(input.split("\\s+")[2]);
        String id = input.split("\\s+")[3];
        String birthDate = input.split("\\s+")[4];

        return new Citizen(name, age, id, birthDate);
    }
}
