package E03Inheritance.P05Restaurant;

import java.math.BigDecimal;

public class Cake extends Dessert{
    public Cake(String name) {
        super(name, new BigDecimal(5), 250, 1000);
    }
}
