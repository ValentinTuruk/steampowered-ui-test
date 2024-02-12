package framework.helpers;

import framework.Browser;
import framework.Setup;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static framework.utils.PropertiesReader.getConfigProperty;

public final class LanguageManager {
    private static WebDriver driver = Setup.driver;
    private static String currentLanguage;
    
    public static String getApplicatoinLanguage() {
        WebElement htmlTag = driver.findElement(By.tagName("html"));
        return htmlTag.getAttribute("lang");
    }
    
    public static String getCurrentLanguage() {
        return currentLanguage;
    }
    
    public static void setCurrentLanguage(String language) {
        currentLanguage = language;
    }
    
    public static String getLocalName(String option) {
        return PropertiesReader.getProperty(String.format("localization%s%s", File.separator, getCurrentLanguage()), option);
    }
    
    public static void waitAndSaveLanguage(String languageCode) {
        waitForLanguageChanges(languageCode);
        setCurrentLanguage(languageCode);
        Browser.waitForPageToLoad();
    }
    
    private static void waitForLanguageChanges(String languageCode) {
        var timer = Duration.ofSeconds(Long.valueOf(getConfigProperty("middle.page.waiter")));
        var wait = new WebDriverWait(driver, timer);
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            return getApplicatoinLanguage().equals(languageCode);
        });
    }
}
