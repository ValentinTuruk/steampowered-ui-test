package framework.elements;

import framework.helpers.LanguageManager;
import streampowered.optionnames.LanguageNames;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class List extends BaseElement {
    public List(String xpathOfElement) {
        super(xpathOfElement);
    }
    
    private void waitForElement(WebDriverWait wait, String optionName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, optionName)));
    }
    
    public void waitForElementMiddleTime(String optionName) {
        waitForElement(middleWaiterOfElement, optionName);
    }
    
    public void setLanguageOption(String optionName) {
        click(LanguageNames.valueOf(optionName).getLanguageName());
        LanguageManager.waitForLanguageChanges(middleWaiterOfElement, LanguageNames.valueOf(optionName).getLanguageAbbreviation());
        LanguageManager.setCurrentLanguage(LanguageNames.valueOf(optionName).getLanguageAbbreviation());
        waitForPageToLoad();
    }
    
    public void hoverOverElement(String optionName) {
        var action = new Actions(driver);
        waitForElementMiddleTime(optionName);
        action.moveToElement(element).perform();
    }
    
    public void click(String optionName) {
        waitForElementMiddleTime(optionName);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        hoverOverElement(optionName);
        element.click();
    }
    
}
