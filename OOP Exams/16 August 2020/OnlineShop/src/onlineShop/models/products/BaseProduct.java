package onlineShop.models.products;

import onlineShop.common.constants.ExceptionMessages;

public abstract class BaseProduct implements Product {
    private static final int ZERO_CONSTANT = 0;

    private int id;
    private String manufacturer;
    private String model;
    private double price;
    private double overallPerformance;

    public BaseProduct(int id, String manufacturer, String model, double price, double overallPerformance) {
        this.setId(id);
        this.setManufacturer(manufacturer);
        this.setModel(model);
        this.setPrice(price);
        this.setOverallPerformance(overallPerformance);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getOverallPerformance() {
        return this.overallPerformance;
    }

    public void setId(int id) {
        if (id <= ZERO_CONSTANT) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRODUCT_ID);
        }
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        if(manufacturer == null || manufacturer.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MANUFACTURER);
        }
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        if(model == null || model.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MODEL);
        }
        this.model = model;
    }

    public void setPrice(double price) {
        if(price <= ZERO_CONSTANT){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    public void setOverallPerformance(double overallPerformance) {
        if(overallPerformance <= ZERO_CONSTANT){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_OVERALL_PERFORMANCE);
        }
        this.overallPerformance = overallPerformance;
    }

    @Override
    public String toString(){
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)",
                this.getOverallPerformance(),
                this.getPrice(),
                this.getClass().getSimpleName(),
                this.getManufacturer(),
                this.getModel(),
                this.getId());
    }
}
