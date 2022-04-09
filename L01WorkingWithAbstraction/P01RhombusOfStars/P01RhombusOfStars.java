package L01WorkingWithAbstraction.P01RhombusOfStars;

import java.util.Scanner;

public class P01RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        printTopHalf(size);
        printBottomHalf(size);
    }

    private static void printBottomHalf(int size) {
        for (int stars = size - 1; stars > 0; stars--) {
            printStarsInOneRow(size, stars);
        }
    }

    private static void printTopHalf(int size) {
        for (int stars = 1; stars <= size; stars++) {
            printStarsInOneRow(size, stars);
        }
    }

    private static void printStarsInOneRow(int size, int stars) {
        for (int i = 0; i < size - stars; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < stars - 1; i++) {
            System.out.print("* ");
        }
        System.out.println("*");
    }
}

