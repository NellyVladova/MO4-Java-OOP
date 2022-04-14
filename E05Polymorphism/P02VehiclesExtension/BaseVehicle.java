package E05Polymorphism.P02VehiclesExtension;

import java.text.DecimalFormat;

public class BaseVehicle implements Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;

    public BaseVehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getTankCapacity() { return tankCapacity; }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setTankCapacity(double tankCapacity) { this.tankCapacity = tankCapacity; }

    @Override
    public String drive(double distance) {
        double neededFuel = getFuelConsumption() * distance;
        if (neededFuel > this.fuelQuantity) {
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        } else {
            this.fuelQuantity = this.fuelQuantity - neededFuel;
        }
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), decimalFormat.format(distance));
    }

    @Override
    public void refuel(double liters) {
        if (liters <= 0) {
            System.out.println("Fuel must be a positive number");
        }else if (liters + getFuelQuantity() > getTankCapacity()) {
            System.out.println("Cannot fit fuel in tank");
        }else {
            this.fuelQuantity += liters;
        }
    }
}
