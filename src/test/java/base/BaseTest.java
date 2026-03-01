
package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

public class BaseTest {

    protected Logger logger = Log.getLogger(this.getClass());
    protected ExtentReports extent;
    protected ExtentTest test;
    protected ConfigReader config;
    private long startTime;

    @BeforeSuite(alwaysRun = true)
    public void startReport() {

        startTime = System.currentTimeMillis();
        extent = ExtentManager.getInstance();

        extent.setSystemInfo("Executed By", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", JsonConfigReader.getEnv());
        extent.setSystemInfo("Browser", JsonConfigReader.getBrowser());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, ITestResult result) throws CustomExceptions.ConfigurationChecked {

        logger.info("===== Test Started: {} =====", method.getName());

        config = new ConfigReader();

        WebDriver driver = DriverFactory.initDriver();
        DriverManager.setDriver(driver);

        logger.info("Browser launched successfully");

        driver.get(JsonConfigReader.requireBaseUrl());

        logger.info("Navigated to URL: {}", JsonConfigReader.getBaseUrl());

        // 🔥 Detect retry attempt count
        Object retryCount = result.getAttribute("retryCount");
        int retryNumber = retryCount == null ? 0 : (int) retryCount;

        String testName = method.getName();
        if (retryNumber > 0) {
            testName = testName + " (Retry " + retryNumber + ")";
        }

        // 🔥 Create Extent node with retry index
        test = ExtentTestManager.createTest(testName);
        test.assignAuthor(System.getProperty("user.name"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        WebDriver driver = DriverManager.getDriver();
        ExtentTest test = ExtentTestManager.getTest();

        try {
            String screenshotPath;

            if (result.getStatus() == ITestResult.FAILURE) {

                // 🔥 Pass retry count to next retry attempt
                int retryCount = result.getMethod().getCurrentInvocationCount();
                result.setAttribute("retryCount", retryCount);

                test.fail("Test Failed");
                if (result.getThrowable() != null) {
                    test.fail(result.getThrowable());
                }

                screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
                test.addScreenCaptureFromPath(screenshotPath);

            } else if (result.getStatus() == ITestResult.SUCCESS) {

                test.pass("Test Passed");

                screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
                test.addScreenCaptureFromPath(screenshotPath);

            } else {
                test.skip("Test Skipped");
                if (result.getThrowable() != null) {
                    test.skip(result.getThrowable());
                }
            }

        } finally {
            if (driver != null) driver.quit();
            DriverManager.unload();
            ExtentTestManager.unload();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        long endTime = System.currentTimeMillis();
        long durationMs = endTime - startTime;

        long seconds = durationMs / 1000 % 60;
        long minutes = durationMs / (1000 * 60) % 60;

        extent.setSystemInfo("Total Execution Time", minutes + "m " + seconds + "s");
        extent.flush();
    }

    // Use DriverManager for driver access
    protected WebDriver driver() {
        return DriverManager.getDriver();
    }
}
