package framework.elements;

import framework.Browser;
import framework.Setup;
import framework.helpers.LanguageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static framework.utils.PropertiesReader.getConfigProperty;

public abstract class BaseElement extends Setup {
    protected String xpathOfElement;
    protected WebElement element;
    
    public BaseElement(String xpathOfElement) {
        this.xpathOfElement = xpathOfElement;
    }
    
    public static void checkBasePageElement(String xpathPrimaryElement) {
        var baseElement = driver.findElements(By.xpath(xpathPrimaryElement)).stream().findFirst().orElse(null);
        Assert.assertNotNull(baseElement, "The page did not load successfully.");
    }
    
    private Duration middleTime = Duration.ofSeconds(Long.valueOf(getConfigProperty("middle.element.waiter")));
    WebDriverWait middleWaiterOfElement = new WebDriverWait(driver, middleTime);
    
    private void waitForElement(WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
        element = driver.findElement(By.xpath(xpathOfElement));
    }
    
    private void waitForElement(WebDriverWait wait, String optionName) {
        optionName = LanguageManager.getLocalName(optionName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpathOfElement, optionName))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, optionName)));
    }
    
    public void waitForElementMiddleTime() {
        waitForElement(middleWaiterOfElement);
    }
    
    public void waitForElementMiddleTime(String optionName) {
        waitForElement(middleWaiterOfElement, optionName);
    }
    
    public void hoverOverElement() {
        var action = new Actions(driver);
        waitForElementMiddleTime();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void hoverOverElement(String optionName) {
        var action = new Actions(driver);
        waitForElementMiddleTime(optionName);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void click() {
        hoverOverElement();
        element.click();
    }
    
    public void click(String optionName) {
        hoverOverElement(optionName);
        element.click();
    }
    
    public void clickAndWait() {
        click();
        Browser.waitForPageToLoad();
    }
    
    public void clickAndWait(String optionName) {
        click(optionName);
        Browser.waitForPageToLoad();
    }
    
    public void scrollToCenter() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, (document.body.scrollHeight - window.innerHeight) / 2);");
    }
    
    public void scrollIntoView() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
    
    public String getElementText() {
        waitForElementMiddleTime();
        return element.getText();
    }
    
    public WebElement getInnerElement(String xpathOfElement) {
        return element.findElement(By.xpath(xpathOfElement));
    }
}