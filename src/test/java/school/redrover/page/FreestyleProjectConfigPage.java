package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.base.BaseConfigPage;
import school.redrover.runner.TestUtils;

public class FreestyleProjectConfigPage extends BaseConfigPage {

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FreestyleProjectProjectPage createProjectPage() {
        return new FreestyleProjectProjectPage(getDriver());
    }

    public FreestyleProjectConfigPage addBuildStep(String buildStep) {
        getDriver().findElement(By.xpath("//button[contains(text(),'Add build step')]")).click();
        getDriver().findElement(By.xpath("//button[contains(text(),'%s')]".formatted(buildStep))).click();

        return this;
    }

    public FreestyleProjectConfigPage addExecuteWindowsBatchCommand(String command) {
        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Build Steps')]/.."))).click();
        TestUtils.scrollToBottom(getDriver());
        addBuildStep("Execute Windows batch command");
        getDriver().findElement(By.xpath("//textarea[@name='command']"))
                .sendKeys(command);

        return this;
    }
}
