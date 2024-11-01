package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PageElementsTest extends BaseTest {

    @Test
    public void newItem (){
       String newItemElement = getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).getText();
       Assert.assertEquals(newItemElement, "New Item");
    }

    @Test
    public void buildHistory (){
        String buildHistory = getDriver().findElement(By.xpath("//*[@id='tasks']/div[2]/span/a")).getText();
        Assert.assertEquals(buildHistory, "Build History");
    }

    @Test
    public void manageJenkins (){
        String manageJenkins = getDriver().findElement(By.xpath("//*[@id='tasks']/div[3]/span/a")).getText();
        Assert.assertEquals(manageJenkins, "Manage Jenkins");
    }

    @Test
    public void myView (){
        String myView = getDriver().findElement(By.xpath("//*[@id='tasks']/div[4]/span/a")).getText();
        Assert.assertEquals(myView, "My Views");
    }

    @Test
    public void addDescription (){
        String addDescription = getDriver().findElement(By.xpath("//*[@id='description-link']")).getText();
        Assert.assertEquals(addDescription, "Add description");
    }

    @Test
    public void writeDescription (){
        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();
        getDriver().findElement(By.name("description")).sendKeys("The test");
        getDriver().findElement(By.name("Submit")).click();

        String getDescription = getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText();
        Assert.assertEquals(getDescription, "The test");
    }

    @Test
    public void newItemValidationName (){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[2]/div[2]/div")).click();

        String validationText = getDriver().findElement(By.id("itemname-required")).getText();
        Assert.assertEquals(validationText, "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void newItemValidationOKButton (){
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        boolean state = getDriver().findElement(By.id("ok-button")).isEnabled();

        Assert.assertFalse(state);
    }

}