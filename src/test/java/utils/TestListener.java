
package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Do NOT create a test here. It is already created in @BeforeMethod.
        // If you want to log something:
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.info("Test started");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.pass("Test Passed ✅");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        WebDriver driver = DriverManager.getDriver();

        if (test != null) {
            // Full stacktrace
            if (result.getThrowable() != null) {
                test.fail(result.getThrowable());
            } else {
                test.fail("Test Failed");
            }

            // Screenshot to the SAME node
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver,
                    result.getMethod().getMethodName());
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.warning("Screenshot attach failed: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.skip("Test Skipped ⚠");
            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            }
        }
    }
}
