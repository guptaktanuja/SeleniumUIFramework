
package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CustomExceptions;
import utils.DriverManager;
import utils.Waits;
import utils.CustomExceptions;



import java.time.Duration;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize PageFactory elements
        PageFactory.initElements(driver, this);
    }



    protected void type(WebElement element, String text) {
        try {
            Waits.waitForVisible(element).clear();
            element.sendKeys(text);
        } catch (TimeoutException e) {
            throw new CustomExceptions.ElementNotFoundException("TYPE failed: element not visible -> " + element);
        } catch (StaleElementReferenceException e) {
            throw new CustomExceptions.ElementNotFoundException("TYPE failed: element stale -> " + element);
        }
    }


    protected void click(WebElement element) {

        try {
            Waits.waitForClickable(element).click();
        } catch (TimeoutException | ElementClickInterceptedException e) {

            throw new RuntimeException("CLICK failed: element not clickable -> " + element, e);

            // throw new CustomExceptions.ElementNotClickableException("CLICK failed: element not clickable -> " + element,e);
        } catch (StaleElementReferenceException e) {
            throw new CustomExceptions.ElementNotClickableException("CLICK failed: element became stale -> " + element,e);
        }
    }


    protected String getText(WebElement element) {
        return Waits.waitForVisible(element).getText();
    }



    private static JavascriptExecutor js() {
        return (JavascriptExecutor) DriverManager.getDriver();
    }



    public static void jsClick(WebElement element) {
        js().executeScript("arguments[0].click();", element);
    }


    public static void scrollIntoView(WebElement element) {
        js().executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", element);
    }


    public static void scrollToBottom() {
        js().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /** Scroll to the top of the page */
    public static void scrollToTop() {
        js().executeScript("window.scrollTo(0, 0);");
    }


    protected void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    protected String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    protected void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String getParentWindow() {
        return driver.getWindowHandle();
    }

    protected void switchToChildWindow(String parentWindow) {
        Set<String> windows = driver.getWindowHandles();
        for (String win : windows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }
    }

    protected void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    protected void closeCurrentWindow() {
        driver.close();
    }

    protected void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    protected void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    protected void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }
}
