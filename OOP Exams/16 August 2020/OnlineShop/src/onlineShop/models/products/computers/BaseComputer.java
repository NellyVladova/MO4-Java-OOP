package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;


    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        double componentsPerformance = components.stream()
                        .mapToDouble(Component::getOverallPerformance)
                        .average()
                        .orElse(0);

        return super.getOverallPerformance() + componentsPerformance;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + components.stream().mapToDouble(Component::getPrice).sum() +
                peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if(this.components.contains(component)){
            String exceptionMessage = String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId());
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        if (components.stream().noneMatch(component -> component.getClass().getSimpleName().equals(componentType))) {
            String exceptionMessage = String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId());
            throw new IllegalArgumentException(exceptionMessage);
        }

        int indexToRemove = 0;

        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            if (component.getClass().getSimpleName().equals(componentType)) {
                indexToRemove = i;
                break;
            }
        }

        return components.remove(indexToRemove);
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if(this.peripherals.contains(peripheral)){
            String exceptionMessage = String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId());
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        if (peripherals.stream().noneMatch(peripheral -> peripheral.getClass().getSimpleName().equals(peripheralType))) {
            String exceptionMessage = String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId());
            throw new IllegalArgumentException(exceptionMessage);
        }

        int indexToRemove = 0;

        for (int i = 0; i < peripherals.size(); i++) {
            Peripheral peripheral = peripherals.get(i);
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                indexToRemove = i;
                break;
            }
        }

        return peripherals.remove(indexToRemove);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append(System.lineSeparator());
        sb.append(String.format(" " + OutputMessages.COMPUTER_COMPONENTS_TO_STRING, components.size()))
                .append(System.lineSeparator());

        for (Component component : this.components) {
            sb.append("  ").append(component.toString()).append(System.lineSeparator());
        }
        sb.append(String.format(" " + OutputMessages.COMPUTER_PERIPHERALS_TO_STRING,
                peripherals.size(),
                peripherals.stream().mapToDouble(Peripheral::getOverallPerformance)
                        .average().orElse(0)))
                .append(System.lineSeparator());

        for (Peripheral peripheral : this.peripherals) {
            sb.append("  ").append(peripheral.toString()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}