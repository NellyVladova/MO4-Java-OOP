package E02Encapsulation.P03ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(!name.trim().isEmpty()) {
            this.name = name;
        }else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    private void setMoney(double money) {
        if(money >= 0) {
            this.money = money;
        }else {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public void buyProduct(Product product) throws Exception {
        if(this.money >= product.getCost()){
            products.add(product);
            this.money-=product.getCost();
        }else {
            throw new IllegalArgumentException(this.name + " can't afford " + product.getName());
        }
    }

}
