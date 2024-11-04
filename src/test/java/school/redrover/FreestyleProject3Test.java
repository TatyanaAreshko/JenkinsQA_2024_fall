package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class FreestyleProject3Test extends BaseTest {

    @Test
    public void testCreateProjectViaCreateJobButton() {

        String newFreestyleProjectName = "FreestyleProject fall2024";

        WebElement createJobButton = getDriver().findElement(By.xpath("//a[@href='newJob']"));
        createJobButton.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement newItemNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        newItemNameField.sendKeys(newFreestyleProjectName);

        WebElement freestyleProjectItemType = getDriver().findElement(
                By.xpath("//li[contains(@class, 'FreeStyleProject')]"));
        freestyleProjectItemType.click();

        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@name='Submit']")));
        saveButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Permalinks']")));

        String actualName = getDriver().findElement(By.tagName("h1")).getText();

        Assert.assertEquals(actualName, newFreestyleProjectName);
    }
}
