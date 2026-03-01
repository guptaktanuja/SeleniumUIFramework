import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumCheckTest {

    @Test
    public void verifySeleniumWorks() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}