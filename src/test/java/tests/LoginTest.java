
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ExcelUtil;

public class    LoginTest extends BaseTest {

     @Test
    public void loginValidUser_UI01() {
        ExcelUtil.loadExcel("RegressionTestData.xlsx", "Login");

        int row = ExcelUtil.getRowNumberByType("valid");
        String username = ExcelUtil.getCellData(row, 1);
        String password = ExcelUtil.getCellData(row, 2);

        test.info("Login to app using "+username+" and password--"+password);
        InventoryPage page = new LoginPage()
                .open()
                .loginAs(username, password);
         test.info("Verify that user is navigated to inventory page.");

        Assert.assertTrue(page.getInventoryPageTitle().contains("Products"));
    }

    @Test
    public void loginInvalidUser_UI02() {
        ExcelUtil.loadExcel("RegressionTestData.xlsx", "Login");

        int row = ExcelUtil.getRowNumberByType("invalid");
        String username = ExcelUtil.getCellData(row, 1);
        String password = ExcelUtil.getCellData(row, 2);
        test.info("Login to app using "+username+" and password--"+password);

        LoginPage loginPage =new LoginPage();
        loginPage
                .open()
                .loginAs(username, password);

        String error = loginPage.getInvalidErrorMessage();
        test.info("Verify that invalid user error message is displayed.");

        Assert.assertTrue(error.equalsIgnoreCase("Epic sadface: Username and password do not match any user in this service"),"user or password name is not as expected");
    }

    @Test
    public void loginLockedUser_UI03() {
        ExcelUtil.loadExcel("RegressionTestData.xlsx", "Login");

        int row = ExcelUtil.getRowNumberByType("locked");
        String username = ExcelUtil.getCellData(row, 1);
        String password = ExcelUtil.getCellData(row, 2);
        test.info("Login to app using "+username+" and password--"+password);

        LoginPage loginPage = new LoginPage().open();
        loginPage.loginAs(username, password);
        String error = loginPage.getInvalidErrorMessage();

        test.info("Verify that locked out user error message is displayed.");

        Assert.assertTrue(error.toLowerCase().contains("locked"));
    }
}


