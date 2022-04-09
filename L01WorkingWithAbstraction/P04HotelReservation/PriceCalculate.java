package L01WorkingWithAbstraction.P04HotelReservation;

public class PriceCalculate {
    public static double calculateTotalPrice(double pricePerDay, int numberOfDays,
                                             Season season, DiscountType discountType) {
        double totalPrice = pricePerDay * numberOfDays;
        totalPrice *= season.getMultiplier();
        totalPrice *= (100 - discountType.getDiscountPercent()) / 100.0;
        return totalPrice;
    }
}

