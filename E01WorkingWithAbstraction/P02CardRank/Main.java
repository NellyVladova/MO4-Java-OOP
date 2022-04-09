package E01WorkingWithAbstraction.P02CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        System.out.println(input + ":");
        CardRanks[] cardRanks = CardRanks.values();
        for (CardRanks cardRank : cardRanks){
            System.out.printf("Ordinal value: %d; Name value: %s\n", cardRank.getValue(), cardRank);
        }
    }
}
