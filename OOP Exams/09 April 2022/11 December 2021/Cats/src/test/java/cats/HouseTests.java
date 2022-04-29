package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private House house;
    private Cat firstCat;
    private Cat secondCat;
    
    @Before
    public void setUp(){
        this.house = new House("HelloKitty", 2);
        this.firstCat = new Cat("Tom");
        this.secondCat = new Cat("Fluffy");
        this.house.addCat(firstCat);
    }

    @Test
    public void testReturnHouseNameSuccessfully(){
        String returned = this.house.getName();
        Assert.assertEquals("HelloKitty", returned);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidHouseName(){
        new House(" ", 5);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetNullHouseName(){
        new House(null, 5);
    }
    @Test
    public void testShouldSetHouseNameSuccessfully(){
        Assert.assertEquals("HelloKitty", this.house.getName());
    }
    @Test
    public void testReturnHouseCapacitySuccessfully(){
        int returned = this.house.getCapacity();
        Assert.assertEquals(2, returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidCapacityToTheHouse(){
        new House("HelloCat", -10);
    }
    @Test
    public void testShouldSetHouseCapacitySuccessfully(){
        Assert.assertEquals(2, this.house.getCapacity());
    }
    @Test
    public void testReturnCatsCountSuccessfully(){
        int returned = this.house.getCount();
        Assert.assertEquals(1, returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddCatToFullHouse(){
        this.house.addCat(secondCat);
        Cat thirdCat = new Cat("Pesho");
        this.house.addCat(thirdCat);
    }
    @Test
    public void testShouldAddCatSuccessfully(){
        this.house.addCat(secondCat);
        Assert.assertEquals(2, this.house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToRemoveNotExistingCat(){
        this.house.removeCat("Pesho");
    }
    @Test
    public void testShouldRemoveExistingCat(){
        this.house.addCat(secondCat);
        this.house.removeCat(secondCat.getName());
        Assert.assertEquals(1, this.house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToSaleNotExistingCat(){
        this.house.catForSale("Pesho");
    }
    @Test
    public void testShouldSaleCatSuccessfully(){
        this.house.addCat(secondCat);
        Cat returned = this.house.catForSale("Fluffy");
        Assert.assertEquals(secondCat, returned);
    }
    @Test
    public void testShouldReturnStatisticsCorrectly(){
        this.house.addCat(secondCat);
        String expected = "The cat Tom, Fluffy is in the house HelloKitty!";
        String returned = this.house.statistics();
        Assert.assertEquals(expected, returned);
    }
    @Test
    public void testShouldSetHungryToFalseWhenCatIsSold(){
        Cat returned = this.house.catForSale("Tom");
        Assert.assertFalse(returned.isHungry());
    }
}
