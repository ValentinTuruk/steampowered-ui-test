package framework;

import framework.utils.DownloadMaster;
import framework.utils.SoftAsserts;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Objects;

import static framework.utils.PropertiesReader.getConfigProperty;

public abstract class Setup {
    public static WebDriver driver;
    
    @BeforeSuite
    protected void start() {
        driver = BrowserFactory.getInstance(Objects.requireNonNull(getConfigProperty("browser.name")));
        DownloadMaster.cleanDownloadedFiles();
        SoftAsserts.cleanSoftAsserts();
    }
    
    @AfterSuite
    protected void finish() {
        if (driver != null) {
            driver.quit();
        }
        SoftAsserts.checkSoftAsserts();
    }
}
