package L08ExceptionsAndErrorHandling;

import java.util.Scanner;

public class P01NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] range = scanner.nextLine().split("\\s+");
        int startNum = Integer.parseInt(range[0]);
        int endNum = Integer.parseInt(range[1]);

        System.out.printf("Range: [%d...%d]\n", startNum, endNum);

        int number = readNumber(scanner, startNum, endNum);

        System.out.println("Valid number: " + number);
    }

    private static int readNumber(Scanner scanner, int startNumber, int endNumber) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= startNumber && number <= endNumber)
                    return number;
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid number: " + input);
            }
        }
    }
}
