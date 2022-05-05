package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut firstAstronaut;
    private Astronaut secondAstronaut;

    @Before
    public void setUp(){
        this.spaceship = new Spaceship("Moon", 2);
        this.firstAstronaut = new Astronaut("Arnold", 20);
        this.secondAstronaut = new Astronaut("Michael", 10);
        this.spaceship.add(firstAstronaut);
    }
    @Test
    public void testShouldGetAstronautsCountSuccessfully(){
        Assert.assertEquals(1, this.spaceship.getCount());
    }
    @Test
    public void testShouldGetSpaceshipNameSuccessfully(){
        Assert.assertEquals("Moon", this.spaceship.getName());
    }
    @Test
    public void testShouldGetCapacitySuccessfully(){
        Assert.assertEquals(2, this.spaceship.getCapacity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddAstronautToFullSpaceship(){
        this.spaceship.add(secondAstronaut);
        Astronaut newAstronaut = new Astronaut("Peter", 40);
        this.spaceship.add(newAstronaut);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddExistingAstronautToSpaceship(){
        this.spaceship.add(firstAstronaut);
    }
    @Test
    public void testShouldRemoveAstronautSuccessfully(){
        this.spaceship.add(secondAstronaut);
        boolean returned = this.spaceship.remove(firstAstronaut.getName());
        Assert.assertTrue(returned);
    }
    @Test
    public void testShouldReturnFalseWhenRemovingNullAstronaut(){
        boolean returned = this.spaceship.remove(secondAstronaut.getName());
        Assert.assertFalse(returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidCapacityToSpaceship(){
        new Spaceship("Star", -20);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidNameToSpaceship(){
        new Spaceship(null, 20);
    }
}
