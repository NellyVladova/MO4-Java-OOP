package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {
    private GiftFactory giftFactory;
    private Gift firstGift;
    private Gift secondGift;

    @Before
    public void setUp(){
        this.giftFactory = new GiftFactory();
        this.firstGift = new Gift("TeddyBear", 10);
        this.secondGift = new Gift("Ball", 5);
        this.giftFactory.createGift(firstGift);
    }
    @Test
    public void testShouldGetGiftsCountSuccessfully(){
        Assert.assertEquals(1, this.giftFactory.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddExistingGift(){
        this.giftFactory.createGift(firstGift);
    }
    @Test
    public void testShouldAddGiftSuccessfully(){
        this.giftFactory.createGift(secondGift);
        Assert.assertEquals(2, this.giftFactory.getCount());
    }
    @Test
    public void testShouldReturnCorrectMessageAfterAddingGiftSuccessfully(){
        String expected = "Successfully added gift Ball with magic 5.00.";
        String returned = this.giftFactory.createGift(secondGift);
        Assert.assertEquals(expected, returned);
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToRemoveNullGift(){
        this.giftFactory.removeGift(null);
    }
    @Test
    public void testShouldReturnTrueWhenGiftExistsInTheCollection(){
        this.giftFactory.createGift(secondGift);
        boolean returned = this.giftFactory.removeGift("TeddyBear");
        Assert.assertTrue(returned);
    }
    @Test
    public void testShouldReturnFalseWhenGiftNotExistsInTheCollection(){
        boolean returned = this.giftFactory.removeGift("Ball");
        Assert.assertFalse(returned);
    }
    @Test
    public void testShouldGetPresentWithLeastMagicSuccessfully(){
        this.giftFactory.createGift(secondGift);
        Gift returned = this.giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals(this.secondGift, returned);
    }
    @Test
    public void testShouldGetGiftWithTheGivenNameSuccessfully(){
        Gift returned = this.giftFactory.getPresent("TeddyBear");
        Assert.assertEquals(this.firstGift, returned);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection(){
        this.giftFactory.getPresents().clear();
    }
}
