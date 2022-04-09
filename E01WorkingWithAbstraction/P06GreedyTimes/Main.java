package E01WorkingWithAbstraction.P06GreedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long input = Long.parseLong(scanner.nextLine());
        String[] itemsAndQuantities = scanner.nextLine().split("\\s+");

        Map<String, Map<String, Long>> bags = new LinkedHashMap<>();
        long gold = 0;
        long rocks = 0;
        long money = 0;

        for (int i = 0; i < itemsAndQuantities.length; i += 2) {
            String name = itemsAndQuantities[i];
            long quantity = Long.parseLong(itemsAndQuantities[i + 1]);

            String items = getItem(name);

            if (items.equals("")) {
                continue;
            } else if (input < bags.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + quantity) {
                continue;
            }

            switch (items) {
                case "Gem":
                    if (getQuantityOfTheItem(bags, quantity, items, "Gold")) continue;
                    break;
                case "Cash":
                    if (getQuantityOfTheItem(bags, quantity, items, "Gem")) continue;
                    break;
            }

            if (!bags.containsKey(items)) {
                bags.put((items), new LinkedHashMap<>());
            }

            if (!bags.get(items).containsKey(name)) {
                bags.get(items).put(name, 0L);
            }


            bags.get(items).put(name, bags.get(items).get(name) + quantity);
            switch (items) {
                case "Gold":
                    gold += quantity;
                    break;
                case "Gem":
                    rocks += quantity;
                    break;
                case "Cash":
                    money += quantity;
                    break;
            }
        }

        printResults(bags);
    }

    private static void printResults(Map<String, Map<String, Long>> bags) {
        for (var entry : bags.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", entry.getKey(), sumValues));

            entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static boolean getQuantityOfTheItem(Map<String, Map<String, Long>> bags, long quantity, String items, String gold2) {
        if (!bags.containsKey(items)) {
            if (bags.containsKey(gold2)) {
                if (quantity > bags.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                    return true;
                }
            } else {
                return true;
            }
        } else if (bags.get(items).values().stream().mapToLong(e -> e).sum() + quantity > bags.get(gold2).values().stream().mapToLong(e -> e).sum()) {
            return true;
        }
        return false;
    }

    private static String getItem(String name) {
        String item = "";
        if (name.length() == 3) {
            item = "Cash";
        } else if (name.toLowerCase().endsWith("gem")) {
            item = "Gem";
        } else if (name.toLowerCase().equals("gold")) {
            item = "Gold";
        }
        return item;
    }
}

