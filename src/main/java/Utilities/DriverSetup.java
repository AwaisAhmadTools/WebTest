package Utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.time.Duration;

public class DriverSetup {

    private WebDriver driver;
    private final String browser = System.getProperty("browser", "chrome");

    public WebDriver initialiseDriver() throws MalformedURLException {

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            driver = new ChromeDriver();
           // ChromeOptions chromeOptions = new ChromeOptions();
           // driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"),chromeOptions);


        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            //FirefoxOptions firefoxOptions = new FirefoxOptions();
           // WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub/"), firefoxOptions);
        } else {
            Assert.fail("Browser " + browser + " is not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getBrowser() {
        return browser;
    }

}
