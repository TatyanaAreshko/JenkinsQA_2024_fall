package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.page.HomePage;
import school.redrover.runner.BaseTest;

public class RunProjectViaBuildNowTest extends BaseTest {

    private static final String PROJECT_NAME = "Test";

    @Test
    public void testNotificationBarAppears() {
        String notificationBar = new HomePage(getDriver())
                .createFreestyleProject(PROJECT_NAME)
                .clickBuildNowViaDropdown(PROJECT_NAME)
                .getNotificationBarStatus();

        Assert.assertEquals(notificationBar, "Build Now: Done.");
    }

    @Test(dependsOnMethods = "testNotificationBarAppears")
    public void testCounterOfRunsIncrease() {
        String progressBar = new HomePage(getDriver())
                .clickBuildNowViaDropdown(PROJECT_NAME)
                .refreshAfterBuild()
                .getNumberOfRuns();

        Assert.assertEquals(progressBar, "#2");
    }

    @Test(dependsOnMethods = "testCounterOfRunsIncrease")
    public void testStatusOnHomePageIsSuccess() {
        final String status = "Success";

        String statusBuild = new HomePage(getDriver())
                .getStatusBuild(PROJECT_NAME,status);

        Assert.assertEquals(statusBuild,status);
    }
}