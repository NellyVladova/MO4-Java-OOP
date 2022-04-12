package E03Inheritance.P05Restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish{
    public Salmon(String name, BigDecimal price) {
        super(name, price, 22);
    }
}
