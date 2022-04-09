package L02Encapsulation.P02SalaryIncrease;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] inputInfo = scanner.nextLine().split("\\s+");
            String firstName = inputInfo[0];
            String lastName = inputInfo[1];
            int age = Integer.parseInt(inputInfo[2]);
            double salary = Double.parseDouble(inputInfo[3]);
            Person person = new Person(firstName, lastName, age, salary);
            personList.add(person);
        }
        double percentage = Double.parseDouble(scanner.nextLine());

        for (Person person : personList){
            person.increaseSalary(percentage);
            System.out.println(person.toString());
        }
    }
}
