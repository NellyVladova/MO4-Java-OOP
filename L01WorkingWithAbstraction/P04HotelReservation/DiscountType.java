package L01WorkingWithAbstraction.P04HotelReservation;

public enum DiscountType {
    VIP(20),
    SecondVisit(10),
    None(0);

    private int discountPercent;

    DiscountType(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}

