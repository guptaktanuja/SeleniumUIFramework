
// tests/InventorySortTest.java
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyNamesAreSortedAtoZ() {

        test.info("getting data for login");
        ExcelUtil.loadExcel("RegressionTestData.xlsx", "Sheet1");
        String username = ExcelUtil.getCellData(1, 0);
        String password = ExcelUtil.getCellData(1, 1);

        logger.info("Username from Excel: {}", username);
        //  test.info("Username from Excel: {}", username);
        // test.info("Username from Excel: {}", username);
        logger.info("Password from Excel: {}", password);


        InventoryPage inventoryPage = new LoginPage()
                .open()                     // navigate to saucedemo
                .loginAs(username, password);  // BasePage.type + click (via @FindBy WebElements)

        // Precondition: login and land on Inventory page

        List<String> actual = inventoryPage.getProductNames();

        List<String> expected = new ArrayList<>(actual);
        expected.sort(String.CASE_INSENSITIVE_ORDER);  // custom comparator (built-in variant)

        Assert.assertEquals(actual, expected,
                "Product names are not sorted A→Z as expected.");
    }
}
