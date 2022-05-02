package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {
    private Shop shop;
    private Goods good;
    private Goods newGoods;

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.shop = new Shop();
        this.good = new Goods("Doll", "123");
        this.newGoods = new Goods("WoodenTrain", "456");
        this.shop.addGoods("Shelves1", good);
    }
    @Test
    public void testShouldReturnShelves(){
        Map<String, Goods> expected = new LinkedHashMap<>();
        Map<String, Goods> returned = this.shop.getShelves();
        expected.put("Shelves1", good);
        expected.put("Shelves2", null);
        expected.put("Shelves3", null);
        expected.put("Shelves4", null);
        expected.put("Shelves5", null);
        expected.put("Shelves6", null);
        expected.put("Shelves7", null);
        expected.put("Shelves8", null);
        expected.put("Shelves9", null);
        expected.put("Shelves10", null);
        expected.put("Shelves11", null);
        expected.put("Shelves12", null);
        Assert.assertEquals(expected,returned);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection(){
        this.shop.getShelves().clear();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddToNoExistingShelf() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves20",newGoods);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddGoodToTakenShelf() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1",newGoods);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testThrowsExceptionWhenTryingToAddExistingGood() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves2",good);
    }
    @Test
    public void testShouldAddGoodSuccessfully() throws OperationNotSupportedException {
        String returned = this.shop.addGoods("Shelves3",newGoods);
        String expected = "Goods: 456 is placed successfully!";
        Assert.assertEquals(expected,returned);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToRemoveFromNotExistingShelf(){
        this.shop.removeGoods("Shelves20",good);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToRemoveNoExistingGood(){
        this.shop.removeGoods("Shelves5",good);
    }
    @Test
    public void testShouldRemoveGoodSuccessfully() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves3",newGoods);
        String returned = this.shop.removeGoods("Shelves3",newGoods);
        String expected = "Goods: 456 is removed successfully!";
        Assert.assertEquals(expected,returned);
    }
    @Test
    public void testShouldSetNullGoods() {
        shop.removeGoods("Shelves1", good);
        Goods emptySlot = shop.getShelves().get("Shelves1");
        Assert.assertNull(emptySlot);
    }
}