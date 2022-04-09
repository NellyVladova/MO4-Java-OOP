package E01WorkingWithAbstraction.P01CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        CardSuits[] cardSuits = CardSuits.values();
        System.out.println(input + ":");
        for (CardSuits cardSuit : cardSuits){
            System.out.printf("Ordinal value: %d; Name value: %s\n", cardSuit.getValue(), cardSuit);
        }
    }
}
