package L04InterfacesAndAbstraction.P05BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Identifiable> identifiableList = new ArrayList<>();
        while (!input.equals("End")) {
            String[] inputParts = input.split("\\s+");
            Identifiable identifiable;
            if(inputParts.length == 2){
                String model = inputParts[0];
                String id = inputParts[1];

                identifiable = new Robot(model, id);
            }else {
                String name = inputParts[0];
                int age = Integer.parseInt(inputParts[1]);
                String id = inputParts[2];

                identifiable = new Citizen(name, age, id);
            }
            identifiableList.add(identifiable);

            input = scanner.nextLine();
        }
        String fakeDigits = scanner.nextLine();

        System.out.println(identifiableList.stream().map(Identifiable::getId)
                .filter(id -> id.endsWith(fakeDigits))
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
