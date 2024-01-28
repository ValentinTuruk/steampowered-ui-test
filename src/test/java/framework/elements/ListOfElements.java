package framework.elements;

import framework.helpers.LanguageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListOfElements extends BaseElement {
    public ListOfElements(String xpathOfElement) {
        super(xpathOfElement);
    }
    
    public void setLanguageOption(String optionName) {
        click(String.format("%s.name", optionName).toLowerCase());
        LanguageManager.waitForLanguageChanges(middleWaiterOfElement, LanguageManager.getOptionCurrentLocal(String.format("%s.abbreviation", optionName).toLowerCase()));
        LanguageManager.setCurrentLanguage(LanguageManager.getOptionCurrentLocal(String.format("%s.abbreviation", optionName).toLowerCase()));
        waitForPageToLoad();
    }
    
    private void waitForElement(WebDriverWait wait, String optionName) {
        optionName = LanguageManager.getOptionCurrentLocal(optionName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, optionName)));
    }
    
    public void waitForElementMiddleTime(String optionName) {
        waitForElement(middleWaiterOfElement, optionName);
    }
    
    public void hoverOverElement(String optionName) {
        var action = new Actions(driver);
        waitForElementMiddleTime(optionName);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void click(String optionName) {
        hoverOverElement(optionName);
        element.click();
    }
    
    public void clickAndWait(String optionName) {
        click(optionName);
        waitForPageToLoad();
    }
    
}
