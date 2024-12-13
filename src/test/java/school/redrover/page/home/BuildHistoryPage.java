package school.redrover.page.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.page.base.BasePage;

import java.util.List;

public class BuildHistoryPage extends BasePage {

    @FindBy(xpath = "//a[@class='jenkins-table__link model-link']/span")
    private WebElement lastBuildName;

    @FindBy(xpath = "//*[@id='projectStatus']/tbody/tr/td[4]")
    private List<WebElement> statusList;

    @FindBy(xpath = "//div[@class='jenkins-table__cell__button-wrapper']/*[@id]")
    private WebElement buildStatusSign;

    @FindBy(xpath = "//td[text()='broken since this build']")
    private WebElement firstBrokenBuildStatusText;

    public BuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String getBuildName() {
        return lastBuildName.getText();
    }

    public List<String> getListOfStatuses() {

        return statusList.stream().map(WebElement::getText).toList();
    }

    public String getBuildStatusSignColor() {
        return buildStatusSign.getAttribute("id");
    }

    public String getColorOfTextStatusOfFirstFailedBuild() {
        return firstBrokenBuildStatusText.getCssValue("color");
    }

}
