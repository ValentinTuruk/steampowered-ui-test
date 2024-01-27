package framework;

import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static WebDriver driver;
    
    protected static WebDriver getInstance(String browserName) {
        switch (browserName) {
            case "Chrome" -> {
                ChromeOptions options = new ChromeOptions();
//              options.addArguments("--headless");
//              options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-geolocation");
                options.addArguments("incognito");
                options.addArguments("--disable-extensions");
                options.addArguments("--safebrowsing-disable-download-protection");
                driver = new ChromeDriver(options);
            }
            case "Firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
//              options.addArguments("--headless");
//              options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-geolocation");
                options.addArguments("-private");
                options.addArguments("--disable-extensions");
                options.addArguments("--safebrowsing-disable-download-protection");
                driver = new FirefoxDriver(options);
            }
            default -> System.out.println("Selected browser is not exist");
        }
        setAllBrowsersSettings();
        return driver;
    }
    
    private static void setAllBrowsersSettings() {
        driver.manage().window().maximize();
        long timeToWait = Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "middle.element.waiter")));
        driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
    }
}
