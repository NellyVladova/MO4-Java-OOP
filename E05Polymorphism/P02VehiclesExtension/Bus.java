package E05Polymorphism.P02VehiclesExtension;

public class Bus extends BaseVehicle {
    public final static double ADDITIONAL_FULL_BUS_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

}
