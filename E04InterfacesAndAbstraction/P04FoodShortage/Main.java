package E04InterfacesAndAbstraction.P04FoodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Buyer> mapOfBuyers = new HashMap();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            if(input.length == 4){
                addCitizen(input, mapOfBuyers);
            }else {
                addRebel(input, mapOfBuyers);
            }
        }
        String name = scanner.nextLine();

        while (!name.equals("End")) {
            if (mapOfBuyers.containsKey(name)) {
                Buyer buyer = mapOfBuyers.get(name);
                buyer.buyFood();
            }

            name = scanner.nextLine();
        }
        System.out.println(getTotalFoodAmount(mapOfBuyers));
    }

    private static int getTotalFoodAmount(Map<String, Buyer> mapOfBuyers) {
        int allFood = 0;
        for (var element : mapOfBuyers.entrySet()){
            allFood+=(element.getValue().getFood());
        }
        return allFood;
    }

    private static void addCitizen(String[] input, Map<String, Buyer> buyerMap) {
        String name = input[0];
        int age = Integer.parseInt(input[1]);
        String id = input[2];
        String birthDate = input[3];
        Citizen citizen = new Citizen(name, age, id, birthDate);
        buyerMap.put(name, citizen);
    }

    private static void addRebel(String[] input, Map<String, Buyer> buyerMap) {
        String name = input[0];
        int age = Integer.parseInt(input[1]);
        String group = input[2];
        Rebel rebel = new Rebel(name, age, group);
        buyerMap.put(name, rebel);
    }
}
