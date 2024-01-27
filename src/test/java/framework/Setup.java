package framework;

import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Objects;

public class Setup {
    public static WebDriver driver;
    
    @BeforeSuite
    protected void start() {
        driver = BrowserFactory.getInstance(Objects.requireNonNull(PropertiesReader.getProperty("config", "browser.name")));
    }
    
    @AfterSuite
    protected void finish() {
        if (driver != null) {
            driver.quit();
        }
    }
}
