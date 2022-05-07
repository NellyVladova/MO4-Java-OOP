package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private Farm farm;
    private Animal pig;
    private Animal cow;

    @Before
    public void setUp(){
        this.farm = new Farm("HappyDay", 2);
        this.pig = new Animal("Pig", 5);
        this.cow = new Animal("Cow", 10);
        this.farm.add(pig);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddAnimalToFullFarmCapacity(){
        Animal animalToAdd = new Animal("sheep", 15);
        this.farm.add(cow);
        this.farm.add(animalToAdd);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddExistingAnimal(){
        this.farm.add(pig);
    }
    @Test
    public void testShouldAddAnimalSuccessfully(){
        this.farm.add(cow);
        Assert.assertEquals(2, this.farm.getCount());
    }
    @Test
    public void testReturnTrueWhenTryingToRemoveExistingAnimal(){
        this.farm.add(cow);
        boolean returned = this.farm.remove("Cow");
        Assert.assertTrue(returned);
    }
    @Test
    public void testReturnFalseWhenTryingToRemoveNotExistingAnimal(){
        boolean returned = this.farm.remove("Cow");
        Assert.assertFalse(returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidCapacity(){
        new Farm("HappyPig", -10);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetNullNameToTheFarm(){
        new Farm(null, 10);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidNameToTheFarm(){
        new Farm(" ", 10);
    }
    @Test
    public void testReturnCountSuccessfully(){
        this.farm.add(cow);
        int returned = this.farm.getCount();
        Assert.assertEquals(2,returned);
    }
    @Test
    public void testReturnNameSuccessfully(){
        String returned = this.farm.getName();
        Assert.assertEquals("HappyDay",returned);
    }
    @Test
    public void testReturnCapacitySuccessfully(){
        int returned = this.farm.getCapacity();
        Assert.assertEquals(2,returned);
    }
}
