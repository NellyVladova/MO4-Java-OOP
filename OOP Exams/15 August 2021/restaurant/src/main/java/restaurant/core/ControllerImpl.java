package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.BeverageRepositoryImpl;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.TableRepositoryImpl;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = new HealthFoodRepositoryImpl();
        this.beverageRepository = new BeverageRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food;
        if(type.equals("Salad")){
            food = new Salad(name, price);
        }else {
            food = new VeganBiscuits(name, price);
        }
        HealthyFood previouslyAdded = this.healthFoodRepository.foodByName(name);

        if (previouslyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }

        this.healthFoodRepository.add(food);
        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverages;
        if(type.equals("Fresh")){
            beverages = new Fresh(name, counter, brand);
        }else {
            beverages = new Smoothie(name, counter, brand);

        }
        Beverages previouslyAdded = this.beverageRepository.beverageByName(name, brand);

        if (previouslyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }

        this.beverageRepository.add(beverages);
        return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        if(type.equals("Indoors")){
            table = new Indoors(tableNumber, capacity);
        }else {
            table = new InGarden(tableNumber, capacity);

        }
        Table previouslyAdded = this.tableRepository.byNumber(tableNumber);

        if(previouslyAdded != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }

        this.tableRepository.add(table);
        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table notReservedTables = this.tableRepository.getAllEntities()
                .stream().filter(table -> !table.isReservedTable() &&
                        table.getSize() >= numberOfPeople).findFirst()
                .orElse(null);
        if(notReservedTables == null){
           return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        notReservedTables.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, notReservedTables.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = this.healthFoodRepository.foodByName(healthyFoodName);
        if(healthyFood == null){
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverages = this.beverageRepository.beverageByName(name, brand);
        if(beverages == null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }
        table.orderBeverages(beverages);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        table.clear();
        totalMoney+=bill;

        return String.format(OutputMessages.BILL, tableNumber, bill);
    }

    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY, totalMoney);
    }
}
