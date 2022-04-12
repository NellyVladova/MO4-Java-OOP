package E03Inheritance.P05Restaurant;

import java.math.BigDecimal;

public class Coffee extends HotBeverage{
    private double caffeine;

    public Coffee(String name, double caffeine) {
        super(name, new BigDecimal(3.50), 50);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }
}
