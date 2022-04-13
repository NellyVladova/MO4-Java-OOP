package L04InterfacesAndAbstraction.P02CarShop;

public interface Rentable extends Car {
    Integer getMinRentDay();
    Double getPricePerDay();
}
