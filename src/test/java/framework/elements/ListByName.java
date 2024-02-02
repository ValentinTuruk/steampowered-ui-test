package framework.elements;

import framework.Browser;
import framework.helpers.LanguageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class ListByName extends BaseElement {
    public ListByName(String xpathOfElement) {
        super(xpathOfElement);
    }
    
    public void setLanguageOption(String optionName) {
        click(String.format("%s.name", optionName).toLowerCase());
        LanguageManager.waitForLanguageChanges(middleWaiterOfElement, LanguageManager.getLocalName(String.format("%s.abbreviation", optionName).toLowerCase()));
        LanguageManager.setCurrentLanguage(LanguageManager.getLocalName(String.format("%s.abbreviation", optionName).toLowerCase()));
        Browser.waitForPageToLoad();
    }
    
    private void waitForElement(WebDriverWait wait, String optionName) {
        optionName = LanguageManager.getLocalName(optionName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, optionName)));
    }
    
    private void waitForElement(WebDriverWait wait, int number) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, number))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpathOfElement, number))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, number)));
    }
    
    public void waitForElementMiddleTime(String optionName) {
        waitForElement(middleWaiterOfElement, optionName);
    }
    
    public void waitForElementMiddleTime(int number) {
        waitForElement(middleWaiterOfElement, number);
    }
    
    public void hoverOverElement(String optionName) {
        var action = new Actions(driver);
        waitForElementMiddleTime(optionName);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void hoverOverElement(int number) {
        var action = new Actions(driver);
        waitForElementMiddleTime(number);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void click(String optionName) {
        hoverOverElement(optionName);
        element.click();
    }
    
    public void click(int number) {
        hoverOverElement(number);
        element.click();
    }
    
    public void clickAndWait(String optionName) {
        click(optionName);
        Browser.waitForPageToLoad();
    }
    
    public void clickAndWait(int number) {
        click(number);
        Browser.waitForPageToLoad();
    }
    
    public void scrollAndClick(String optionName) {
        scrollToCenter();
        click(optionName);
    }
    
}
