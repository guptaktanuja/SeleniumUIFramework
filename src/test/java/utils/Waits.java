
package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public final class Waits {

    private static final long DEFAULT_TIMEOUT_SEC = 12;

    private Waits() {}

    public static WebDriverWait webDriverWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT_SEC)
        );
    }

    private static WebDriverWait webDriverWait(Duration timeout) {
        return new WebDriverWait(
                DriverManager.getDriver(),
               timeout);
    }


    public static WebElement waitForClickable(WebElement element) {
            return webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        }

    public static WebElement waitForClickable(WebElement element,Duration timeout) {
        return webDriverWait(timeout).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisible(WebElement element) {
        return webDriverWait().until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForPresence(By locator) {
        return webDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}


