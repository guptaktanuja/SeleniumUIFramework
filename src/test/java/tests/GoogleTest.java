/*

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GooglePage;
import utils.DriverManager;
import utils.ExtentTestManager;

public class GoogleTest extends BaseTest {

    @Test
    public void verifySearch() {

        var test = ExtentTestManager.getTest();

        test.info("Typing search keyword");

        GooglePage google = new GooglePage();
      //  google.type("Extent Report Selenium");

        test.info("Search keyword entered");

        String title = DriverManager.getDriver().getTitle();
        Assert.assertTrue(title.contains("Selenium"),
                "Expected Selenium results page, but got: " + title);
    }
}
*/
