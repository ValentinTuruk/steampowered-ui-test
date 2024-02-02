package framework;

import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class BrowserFactory {
    public static WebDriver driver;
    
    public static WebDriver getInstance(String browserName) {
        switch (browserName) {
            case "Chrome" -> {
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", Setup.downloadPath);
                options.setExperimentalOption("prefs", prefs);
                prefs.put("safebrowsing.enabled", "true");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
            }
            case "Firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addPreference("browser.download.folderList", 2);
                options.addPreference("browser.download.dir", Setup.downloadPath);
                options.addArguments("--disable-extensions");
                driver = new FirefoxDriver(options);
            }
            default -> System.out.println("Selected browser is not exist");
        }
        setCommonBrowsersSettings();
        return driver;
    }
    
    private static void setCommonBrowsersSettings() {
        driver.manage().window().maximize();
        long timeToWait = Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "middle.element.waiter")));
        driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
    }
}
