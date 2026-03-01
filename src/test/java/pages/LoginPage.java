
package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private final String baseUrl = "https://www.saucedemo.com/";

    // --- PageFactory elements ---
    @FindBy(id = "user-name")
    private WebElement txtUser;

    @FindBy(id = "password")
    private WebElement txtPass;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    @FindBy(xpath = "//*[@data-test='title']")
    private WebElement inventoryPageTitle;

    @FindBy(xpath = "//*[@data-test='error']")
    private WebElement invalidLoginError;

    public LoginPage open() {
        driver.get(baseUrl);
        getText(txtUser);  // triggers visibility wait
        return this;
    }

    public InventoryPage loginAs(String username, String password) {
        type(txtUser, username);  // <-- BasePage.type(WebElement, String)
        type(txtPass, password);  // <-- BasePage.type(WebElement, String)
        click(btnLogin);          // <-- BasePage.click(WebElement)
        return new InventoryPage();
    }

    public String getInvalidErrorMessage()
    {
        return invalidLoginError.getText();
    }

}
