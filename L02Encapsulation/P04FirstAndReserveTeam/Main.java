package L02Encapsulation.P04FirstAndReserveTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Person> players = new ArrayList<>();
        Team team = new Team("Black Eagles");
        for (int i = 0; i < n; i++) {
            String[] inputInfo = scanner.nextLine().split("\\s+");
            String firstName = inputInfo[0];
            String lastName = inputInfo[1];
            int age = Integer.parseInt(inputInfo[2]);
            double salary = Double.parseDouble(inputInfo[3]);
            Person person = new Person(firstName, lastName, age, salary);
            players.add(person);
        }
        for (Person person : players){
            team.addPlayer(person);
        }
        System.out.printf("First team have %d players\n", team.getFirstTeam().size());
        System.out.printf("Reserve team have %d players\n", team.getReserveTeam().size());
    }
}
