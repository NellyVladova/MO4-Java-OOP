package L02Encapsulation.P01SortByNameAndAge;

import java.util.ArrayList;
import java.util.Collections;
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
            Person person = new Person(firstName, lastName, age);
            personList.add(person);
        }
        Collections.sort(personList, (firstPerson, secondPerson)-> {
            int compareName = firstPerson.getFirstName().compareTo(secondPerson.getFirstName());

            if(compareName != 0){
                return compareName;
            }else {
                int compareAge = Integer.compare(firstPerson.getAge(), secondPerson.getAge());
                return compareAge;
            }
        });
        for (Person person : personList){
            System.out.println(person.toString());
        }
    }
}

