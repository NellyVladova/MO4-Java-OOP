package L01WorkingWithAbstraction.P04HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(input[0]);
        int numberOfDays = Integer.parseInt(input[1]);
        Season season = Season.valueOf(input[2]);
        DiscountType discountType = DiscountType.valueOf(input[3]);

        double totalPrice = PriceCalculate.calculateTotalPrice(pricePerDay, numberOfDays, season, discountType);
        System.out.printf("%.2f", totalPrice);
    }
}

