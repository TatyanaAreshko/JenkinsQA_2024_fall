import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SerovNikitaFirstTest {

    WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void testRemoveTest() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        addElement.click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"elements\"]/button"))
                .getText(), "Delete");

        driver.findElement(By.xpath("//*[@id=\"elements\"]/button")).click();
    }
    @Test
    public void selectCheckboxesTest() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));

        checkbox2.isSelected();

        checkbox1.click();

        checkbox1.isSelected();
    }
    @Test
    public void contextMenuTest() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        Actions action = new Actions(driver);

        WebElement contextItem = driver.findElement(By.id("hot-spot"));
        action.contextClick(contextItem).build().perform();

        Alert displayMessage = driver.switchTo().alert();

        Assert.assertEquals(displayMessage.getText(),"You selected a context menu");
    }

    @Test
    public void drugAndDropTest() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement elementA = driver.findElement(By.id("column-a"));
        WebElement elementB = driver.findElement(By.id("column-b"));

        Actions action = new Actions(driver);
        action.dragAndDrop(elementA,elementB).build().perform();

        Assert.assertEquals(elementA.getText(), "B");
    }
}