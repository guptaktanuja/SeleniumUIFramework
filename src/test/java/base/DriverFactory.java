package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import utils.ConfigReader;
import utils.JsonConfigReader;

public class DriverFactory {

    public static WebDriver initDriver() {

      //  ConfigReader config = new ConfigReader();
        // String browser = config.getProperty("browser");
        String browser = JsonConfigReader.getBrowser();

        boolean headless = JsonConfigReader.isHeadless();

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if (headless) {
                    System.out.println("headless is true");
                    options.addArguments("--headless=new");
                    // Ensure consistent viewport in headless
                    options.addArguments("--window-size=1920,1080");
                } else {
                    // Optional: start maximized in headed mode
                    options.addArguments("--start-maximized");
                }

                driver = new ChromeDriver(options);
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }

            case "edge": {
                // Because your network couldn’t download drivers, point to your local driver under /driver
                System.setProperty("webdriver.edge.driver",
                        System.getProperty("user.dir") + "/drivers/msedgedriver.exe");

                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    System.out.println("[DriverFactory] Headless Edge enabled");
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage");
                } else {
                    options.addArguments("--start-maximized");
                }

                driver = new EdgeDriver(options);
                if (!headless) driver.manage().window().maximize();
                break;
            }

            default:
                throw new RuntimeException("Unsupported browser in config.json. Allowed: chrome, firefox, edge");
        }

        return driver;
    }
}