package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Map<Integer, Component> components;
    private Map<Integer, Peripheral> peripherals;
    private Map<Integer, Computer> computers;

    public ControllerImpl() {
        this.components = new LinkedHashMap<>();
        this.peripherals = new LinkedHashMap<>();
        this.computers = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }
        Computer newComputer;
        switch (computerType){
            case "DesktopComputer":
                newComputer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                newComputer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        computers.put(id, newComputer);
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        computerExist(id);
        if (peripherals.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }
        Peripheral peripheral;
        switch (peripheralType){
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        computers.get(computerId).addPeripheral(peripheral);
        peripherals.put(peripheral.getId(), peripheral);

        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        computerExist(computerId);

        Peripheral peripheral = computers.get(computerId).removePeripheral(peripheralType);
        peripherals.remove(peripheral.getId());
        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        computerExist(computerId);
        if(components.containsKey(id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }
        Component component;
        switch (componentType){
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
        components.put(component.getId(), component);
        computers.get(computerId).addComponent(component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        computerExist(computerId);

        Component component = computers.get(computerId).removeComponent(componentType);
        components.remove(component.getId());

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        computerExist(id);
        Computer computer = computers.remove(id);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> resultList = computers.values()
                .stream()
                .filter(computer -> computer.getPrice() <= budget)
                .sorted(Comparator.comparing(Computer::getOverallPerformance).reversed())
                .collect(Collectors.toList());

        if (resultList.isEmpty()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }
        Computer computer = resultList.get(0);

        computers.remove(computer.getId());

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        computerExist(id);
        return this.computers.get(id).toString();
    }

    private void computerExist(int id) {
        if (!this.computers.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
    }
}
