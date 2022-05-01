package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Player

    private Player player;
    private Gun gun;

    @Before
    public void setUp(){
        this.gun = new Gun("Maroko", 10);
        this.player = new Player("Ivan", 20);
        this.player.addGun(gun);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToSetNullUsername(){
        new Player(" ", 100);
    }
    @Test
    public void testThatCheckSettingUsernameSuccessfully(){
        new Player("Pesho", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToSetInvalidHealth(){
        new Player("Pesho", -12);
    }

    @Test
    public void testThatCheckSettingHealthSuccessfully(){
        new Player("Pesho", 10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection(){
        player.getGuns().remove("Neo100");
    }

    @Test(expected = IllegalStateException.class)
    public void testThrowsExceptionWhenPlayerIsDead(){
        player.takeDamage(100);
        player.takeDamage(100);
    }

    @Test
    public void testThatCheckDecreasingHealthSuccessfully(){
        player.takeDamage(5);
        Assert.assertEquals(15, player.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToAddNullGunToPlayer() {
        gun = null;
        player.addGun(gun);
    }

    @Test
    public void testShouldRemoveGunSuccessfully(){
        boolean isRemoved = player.removeGun(gun);
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void testShouldGetGunSuccessfully(){
        Gun newGun = new Gun("F120", 40);
        player.addGun(newGun);
        Gun result = player.getGun("Maroko");
        Assert.assertEquals(gun, result);
    }
}
