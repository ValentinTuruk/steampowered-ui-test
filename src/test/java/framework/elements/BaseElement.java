package framework.elements;

import framework.Browser;
import framework.Setup;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public abstract class BaseElement extends Setup {
    protected String xpathOfElement;
    protected WebElement element;
    
    public BaseElement(String xpathOfElement) {
        this.xpathOfElement = xpathOfElement;
    }
    
    public static void checkBaseElement(String xpathPrimaryElement) {
        var baseElement = driver.findElements(By.xpath(xpathPrimaryElement)).stream().findFirst().orElse(null);
        Assert.assertNotNull(baseElement, "The page did not load successfully.");
    }
    
    private Duration shortTime = Duration.ofSeconds(Long.valueOf(getConfigProperty("short.element.waiter")));
    private Duration middleTime = Duration.ofSeconds(Long.valueOf(getConfigProperty("middle.element.waiter")));
    private Duration longTime = Duration.ofSeconds(Long.valueOf(getConfigProperty("long.element.waiter")));
    WebDriverWait shortWaiterOfElement = new WebDriverWait(driver, shortTime);
    WebDriverWait middleWaiterOfElement = new WebDriverWait(driver, middleTime);
    WebDriverWait longWaiterOfElement = new WebDriverWait(driver, longTime);
    
    private void waitForElement(WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
        element = driver.findElement(By.xpath(xpathOfElement));
    }
    
    private void waitForOneMoreElement(WebDriverWait wait, String xpathOfElement) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathOfElement)));
        element = driver.findElement(By.xpath(xpathOfElement));
    }
    
    private void waitForElement(WebDriverWait wait, BaseElement elementName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName.xpathOfElement)));
    }
    
    
    public void hoverOverElement() {
        var action = new Actions(driver);
        waitForElementMiddleTime();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    public void waitForElementMiddleTime() {
        waitForElement(middleWaiterOfElement);
    }
    
    public void waitForOneMoreElementMiddleTime(String xpathOfElement) {
        waitForOneMoreElement(middleWaiterOfElement, xpathOfElement);
    }
    
    
    public void waitForElementMiddleTime(BaseElement elementName) {
        waitForElement(middleWaiterOfElement, elementName);
    }
    
    public void click() {
        hoverOverElement();
        element.click();
    }
    
    public void jsClick() {
        hoverOverElement();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    public void scrollToCenter() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, (document.body.scrollHeight - window.innerHeight) / 2);");
    }
    
    public void clickAndWait() {
        click();
        Browser.waitForPageToLoad();
    }
    
    public void jsClickAndWait() {
        jsClick();
        Browser.waitForPageToLoad();
    }
    
    public void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    
    public void scrollIntoView() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
    
    public void scrollIntoViewToCetner() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        double elementPosition = (double) jsExecutor.executeScript(
                "var element = arguments[0];" +
                        "var rect = element.getBoundingClientRect();" +
                        "return rect.top - (window.innerHeight / 2);",
                element);
        jsExecutor.executeScript("window.scrollTo(0, arguments[0]);", elementPosition);
    }
    
    public String getConfigProperty(String key) {
        return PropertiesReader.getProperty("config", key);
    }
    
}