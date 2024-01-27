package framework;

import framework.helpers.LanguageManager;
import framework.utils.PropertiesReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Browser extends Setup {
    public static void navigateTo(String urlName) {
        Duration middleWieter = Duration.ofSeconds(Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "middle.page.waiter"))));
        WebDriverWait wait = new WebDriverWait(driver, middleWieter);
        driver.get(PropertiesReader.getProperty("config", urlName));
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        });
        LanguageManager.setCurrentLanguage(LanguageManager.getApplicatoinLanguage());
    }
}
