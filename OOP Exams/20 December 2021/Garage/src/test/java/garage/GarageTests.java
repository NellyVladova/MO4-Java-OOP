package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Garage garage;
    private Car firstCar;
    private Car secondCar;

    @Before
    public void setUp(){
        this.garage = new Garage();
        this.firstCar = new Car("Toyota", 250, 1000);
        this.secondCar = new Car("Seat", 200, 2000);
        this.garage.addCar(firstCar);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection(){
        this.garage.getCars().clear();
    }
    @Test
    public void testShouldReturnCarCountSuccessfully(){
        int returned = this.garage.getCount();
        Assert.assertEquals(1, returned);
    }
    @Test
    public void testShouldReturnCollectionWithCarsWhichMaxSpeedIsAboveDefiniteSpeed(){
        List<Car> allCarsWithMaxSpeedAbove = garage.findAllCarsWithMaxSpeedAbove(230);
        Assert.assertEquals(firstCar, allCarsWithMaxSpeedAbove.get(0));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddNullCar(){
        this.garage.addCar(null);
    }
    @Test
    public void testShouldAddCarSuccessfully(){
        this.garage.addCar(secondCar);
        Assert.assertEquals(2, this.garage.getCount());
    }
    @Test
    public void testShouldReturnTheMostExpensiveCar(){
        this.garage.addCar(secondCar);
        Car returned = this.garage.getTheMostExpensiveCar();
        Assert.assertEquals(secondCar, returned);
    }
    @Test
    public void testShouldReturnCollectionWithCarsByBrand(){
        this.garage.addCar(secondCar);
        List<Car> returned = this.garage.findAllCarsByBrand("Seat");
        Assert.assertEquals(secondCar, returned.get(0));
    }
}