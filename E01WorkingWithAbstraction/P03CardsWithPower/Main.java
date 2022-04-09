package E01WorkingWithAbstraction.P03CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rank = scanner.nextLine();
        String suit = scanner.nextLine();
        CardRanks cardRanks = CardRanks.valueOf(rank);
        CardSuits cardSuits = CardSuits.valueOf(suit);
        int cardPower = cardRanks.getValue() + cardSuits.getValue();
        System.out.printf("Card name: %s of %s; Card power: %d\n", cardRanks, cardSuits, cardPower);
    }
}

