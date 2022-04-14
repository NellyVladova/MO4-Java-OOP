package E05Polymorphism.P01Vehicles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputInfo = scanner.nextLine().split("\\s+");
        Vehicle car = createVehicle(inputInfo);

        inputInfo = scanner.nextLine().split("\\s+");
        Vehicle truck = createVehicle(inputInfo);

        Map<String, Vehicle> vehicleMap = new HashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            inputInfo = scanner.nextLine().split("\\s+");
            String command = inputInfo[0];
            String vehicleType = inputInfo[1];
            switch (command){
                case "Drive":
                    double distance = Double.parseDouble(inputInfo[2]);
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;
                case "Refuel":
                    double liters = Double.parseDouble(inputInfo[2]);
                    vehicleMap.get(vehicleType).refuel(liters);
                    break;
            }
        }
        System.out.printf("Car: %.2f\n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f\n", truck.getFuelQuantity());
    }

    public static Vehicle createVehicle(String[] inputInfo){
        String vehicleType = inputInfo[0];
        double fuelQuantity = Double.parseDouble(inputInfo[1]);
        double fuelConsumption = Double.parseDouble(inputInfo[2]);
        if (vehicleType.equals("Car")) {
            return new Car(fuelQuantity, fuelConsumption);
        }else {
            return new Truck(fuelQuantity, fuelConsumption);
        }
    }
}
