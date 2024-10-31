package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.ProjectUtils;

import java.time.Duration;

public class RenameJobTest extends BaseTest {

    @Test
    public void tesRenameJob(){

        getDriver().findElement(By.xpath("//*[@id='main-panel']/div[2]/div/section[1]/ul/li/a")).click();
        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("TestBuild");
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-standalone-projects']/ul/li[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button[1]")).click();
        getDriver().findElement(By.xpath("//*[@id='breadcrumbs']/li[1]/a")).click();
        getDriver().findElement(By.xpath("//*[@id='job_TestBuild']/td[3]/a/span")).getText();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']")).getText(), "TestBuild");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

        WebElement jobName = getDriver().findElement(By.xpath("//*[@id='job_TestBuild']/td[3]/a/span"));
        Actions actionsJobName = new Actions(getDriver());
        actionsJobName.moveToElement(jobName);
        ProjectUtils.log("Move to jobName ");

/**
 * *******************************************************************************************************************************************
 */

//        WebElement dropdownChevron = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='job_TestBuild']/td[3]/a/button")));
//        ProjectUtils.log("wait visibilityOf dropdownChevron");
//        Actions actionsDropdownChevron = new Actions(getDriver());
//        ProjectUtils.log("new actionsDropdownChevron");
//        actionsDropdownChevron.moveToElement(dropdownChevron).click().perform();
//        ProjectUtils.log("actionsDropdownChevron click");
/**
 * *******************************************************************************************************************************************
 */

        //*[@id="job_TestBuild"]/td[3]/a/span
        //*[@id="tippy-6"]/div/div/p // индикатор загрузки

/**
 * через javascript воспроизводится ошибка с бесконечной загрузкой
 * *******************************************************************************************************************************************
 */

        WebElement dropdownChevron = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='job_TestBuild']/td[3]/a/button")));
        ProjectUtils.log("wait visibilityOf dropdownChevron");

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", dropdownChevron);
        ProjectUtils.log("JavascriptExecutor click dropdownChevron");
        dropdownChevron.click();
        ProjectUtils.log("getDriver click dropdownChevron");

/**
 * *******************************************************************************************************************************************
 */
        WebElement renameLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]/div/div/div/a[4]")));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", renameLink);
        ProjectUtils.log("javascript scroll to rename link");
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", renameLink);
        ProjectUtils.log("javascript click to rename link");

        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/TestBuild/confirm-rename");
        ProjectUtils.log("assert rename url");

        WebElement inputField = getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[2]/input"));
        ProjectUtils.log("find input field");
        inputField.clear();
        ProjectUtils.log("clear input field");
        inputField.sendKeys("TestBuild_NewName");
        ProjectUtils.log("send keys TestBuild_NewName");

        WebElement renameButton = getDriver().findElement(By.xpath("//*[@id='bottom-sticker']/div/button"));
        ProjectUtils.log("find renameButton");
        Actions actionsRenameButton = new Actions(getDriver());
        actionsRenameButton.moveToElement(renameButton).click().perform();
        ProjectUtils.log("click renameButton");

        getDriver().findElement(By.id("jenkins-home-link")).click();
        ProjectUtils.log("navigate to home page");

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@class='jenkins-table__link model-link inside']"))
                .getText(), "TestBuild_NewName");
        ProjectUtils.log("assert check name  item TestBuild_NewName");

    }

}
