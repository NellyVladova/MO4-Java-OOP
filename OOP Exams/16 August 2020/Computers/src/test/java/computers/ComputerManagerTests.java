package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    // TODO: Test ComputerManager
    //TODO: upload into judge
    private ComputerManager computerManager;
    private Computer computer;

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
        this.computer = new Computer("Lenovo", "A7", 1200);
        computerManager.addComputer(computer);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionWhenTryingToModifyUnmodifiableCollection() {
        this.computerManager.getComputers().clear();
    }

    @Test
    public void testShouldReturnValidCollectionSize() {
        int returned = computerManager.getCount();
        Assert.assertEquals(1, returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToAddExistingComputer() {
        computerManager.addComputer(computer);
    }

    @Test
    public void testShouldAddComputerSuccessfully() {
        Computer newComputer = new Computer("Acer", "Aspire 5", 1500);
        computerManager.addComputer(newComputer);
        int returned = computerManager.getCount();
        Assert.assertEquals(2, returned);
    }

    @Test
    public void testShouldRemoveComputerSuccessfully() {
        computerManager.removeComputer("Lenovo", "A7");
        int returned = computerManager.getCount();
        Assert.assertEquals(0, returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToGetComputerWithNoSuchManufacturer() {
        computerManager.getComputer("Samsung", "S100");
    }

    @Test
    public void testShouldGetComputerWithValidManufacturer() {
        Computer newComputer = new Computer("Samsung", "S100", 2000);
        computerManager.addComputer(newComputer);
        Computer returned = computerManager.getComputer("Samsung", "S100");
        Assert.assertEquals(newComputer, returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionWhenTryingToGetComputerWithNullManufacturer() {
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testShouldGetComputersByManufacturerSuccessfully() {
        List<Computer> computersList = new ArrayList<>();
        Computer newComputer = new Computer("Lenovo", "S100", 2000);
        computersList.add(computer);
        computersList.add(newComputer);
        computerManager.addComputer(newComputer);
        List<Computer> returnedList = computerManager.getComputersByManufacturer("Lenovo");
        Assert.assertEquals(computersList, returnedList);

    }
}