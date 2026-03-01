package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    public static ExtentTest createTest(String name) {
        ExtentReports extent = ExtentManager.getInstance();
        ExtentTest test = extent.createTest(name);
        tlTest.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return tlTest.get();
    }

    public static void unload() {
        tlTest.remove();
    }
}
