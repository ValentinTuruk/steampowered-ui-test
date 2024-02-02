package framework;

import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Objects;

public abstract class Setup {
    public static WebDriver driver;
    public static String downloadPath = String.format("%s%sdownload", System.getProperty("user.dir"), File.separator);
    
    private void cleanDownloadedFiles() {
        File folder = new File(downloadPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
    
    @BeforeSuite
    protected void start() {
        driver = BrowserFactory.getInstance(Objects.requireNonNull(PropertiesReader.getProperty("config", "browser.name")));
        cleanDownloadedFiles();
    }
    
    @AfterSuite
    protected void finish() {
        if (driver != null) {
            driver.quit();
        }
    }
}
