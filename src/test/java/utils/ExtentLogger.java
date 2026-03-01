/*

package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentLogger {

    private static String format(String stepName, String message) {
        return MarkupHelper.createLabel("Step: " + stepName, ExtentColor.TEAL).getMarkup()
                + "<div style='margin-top:4px'>" + message + "</div>";
    }

    public static void info(ExtentTest test, String stepName, String message) {
        test.info(format(stepName, message));
    }

    public static void pass(ExtentTest test, String stepName, String message) {
        test.pass(format(stepName, message));
    }

    public static void fail(ExtentTest test, String stepName, String message) {
        test.fail(format(stepName, message));
    }

}
*/
