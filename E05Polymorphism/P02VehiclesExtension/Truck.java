package E05Polymorphism.P02VehiclesExtension;

public class Truck extends BaseVehicle {
    private final static double ADDITIONAL_AC_CONSUMPTION = 1.6;
    private final static double FUEL_LEAK_CORRECTION_PERCENTAGE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        super.setFuelConsumption(super.getFuelConsumption() + ADDITIONAL_AC_CONSUMPTION);
    }

    @Override
    public void refuel(double liters) {
        liters *= FUEL_LEAK_CORRECTION_PERCENTAGE;
        super.refuel(liters);
    }
}
