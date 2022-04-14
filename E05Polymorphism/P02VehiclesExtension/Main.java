package E05Polymorphism.P02VehiclesExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputInfo = scanner.nextLine().split("\\s+");
        BaseVehicle car = createVehicle(inputInfo);

        inputInfo = scanner.nextLine().split("\\s+");
        BaseVehicle truck = createVehicle(inputInfo);

        inputInfo = scanner.nextLine().split("\\s+");
        BaseVehicle bus = createVehicle(inputInfo);

        Map<String, BaseVehicle> vehicleMap = new HashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            inputInfo = scanner.nextLine().split("\\s+");
            String command = inputInfo[0];
            String vehicleType = inputInfo[1];
            switch (command){
                case "Drive":
                    double distance = Double.parseDouble(inputInfo[2]);
                    Vehicle vehicle = vehicleMap.get(vehicleType);
                    if(vehicle instanceof Bus){
                        bus.setFuelConsumption(bus.getFuelConsumption() + Bus.ADDITIONAL_FULL_BUS_CONSUMPTION);
                        System.out.println(bus.drive(distance));
                        bus.setFuelConsumption(bus.getFuelConsumption() - Bus.ADDITIONAL_FULL_BUS_CONSUMPTION);
                        continue;
                    }
                    System.out.println(vehicle.drive(distance));
                    break;
                case "Refuel":
                    double liters = Double.parseDouble(inputInfo[2]);
                    vehicleMap.get(vehicleType).refuel(liters);
                    break;
                case "DriveEmpty":
                    double distanceToDrive = Double.parseDouble(inputInfo[2]);
                    System.out.println(bus.drive(distanceToDrive));
                    break;
            }
        }
        System.out.printf("Car: %.2f\n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f\n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f\n", bus.getFuelQuantity());
    }

    public static BaseVehicle createVehicle(String[] inputInfo){
        String vehicleType = inputInfo[0];
        double fuelQuantity = Double.parseDouble(inputInfo[1]);
        double fuelConsumption = Double.parseDouble(inputInfo[2]);
        double tankCapacity = Double.parseDouble(inputInfo[3]);
        if (vehicleType.equals("Car")) {
            return new Car(fuelQuantity, fuelConsumption, tankCapacity);
        }else if(vehicleType.equals("Truck")){
            return new Truck(fuelQuantity, fuelConsumption, tankCapacity);
        }else {
            return new Bus(fuelQuantity, fuelConsumption, tankCapacity);
        }
    }
}
