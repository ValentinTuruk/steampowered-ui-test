package framework.helpers;

import framework.Setup;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguageManager {
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
    
    public static void waitForLanguageChanges(WebDriverWait wait, String languageAbbreviation) {
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            return getApplicatoinLanguage().equals(languageAbbreviation);
        });
    }
    
    public static String getOptionCurrentLocal (String option){
        return PropertiesReader.getProperty(String.format("localization/%s", LanguageManager.getCurrentLanguage()),option);
    }
}
