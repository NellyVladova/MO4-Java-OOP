package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero firstHero;
    private Hero secondHero;

    @Before
    public void setUp(){
        this.heroRepository = new HeroRepository();
        this.firstHero = new Hero("Peter", 20);
        this.secondHero = new Hero("Michael", 30);
        this.heroRepository.create(firstHero);
    }

    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToCreateNullHero(){
        this.heroRepository.create(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToCreateHeroWhichExists(){
        this.heroRepository.create(firstHero);
    }
    @Test
    public void testShouldAddHeroSuccessfully(){
        this.heroRepository.create(secondHero);
        Assert.assertEquals(2, heroRepository.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testThrowsExceptionWhenTryingToRemoveNullHero(){
        this.heroRepository.remove(null);
    }
    @Test
    public void testShouldRemoveHeroSuccessfully(){
        this.heroRepository.create(secondHero);
        this.heroRepository.remove("Michael");
        Assert.assertEquals(1, this.heroRepository.getCount());
    }
    @Test
    public void testShouldGetHeroWithHighestLevelSuccessfully(){
        this.heroRepository.create(secondHero);

        Assert.assertEquals(secondHero, this.heroRepository.getHeroWithHighestLevel());
    }
    @Test
    public void testGetHeroWithTheGivenNameSuccessfully(){
        this.heroRepository.create(secondHero);
        Hero returnedHero = heroRepository.getHero("Peter");
        Assert.assertEquals(firstHero, returnedHero);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection(){
        this.heroRepository.create(secondHero);
        this.heroRepository.getHeroes().clear();
    }
}
