package framework.elements;

import framework.Setup;
import framework.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class BaseElement extends Setup {
    protected String xpathOfElement;
    protected WebElement element;
    
    public BaseElement(String xpathOfElement) {
        this.xpathOfElement = xpathOfElement;
    }
    
    public static void checkBaseElement(String xpathPrimaryElement) {
        var baseElement = driver.findElements(By.xpath(xpathPrimaryElement)).stream().findFirst().orElse(null);
        Assert.assertNotNull(baseElement, "The page did not load successfully.");
    }
    
    private Duration shortTime = Duration.ofSeconds(Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "short.element.waiter"))));
    private Duration middleTime = Duration.ofSeconds(Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "middle.element.waiter"))));
    private Duration longTime = Duration.ofSeconds(Long.valueOf(Objects.requireNonNull(PropertiesReader.getProperty("config", "long.element.waiter"))));
    WebDriverWait shortWaiterOfElement = new WebDriverWait(driver, shortTime);
    WebDriverWait middleWaiterOfElement = new WebDriverWait(driver, middleTime);
    WebDriverWait longWaiterOfElement = new WebDriverWait(driver, longTime);
    
    private void waitForElement(WebDriverWait wait) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathOfElement)));
        element = driver.findElement(By.xpath(xpathOfElement));
    }
    
    private void waitForElement(WebDriverWait wait, BaseElement elementName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementName.xpathOfElement)));
    }
    
    public void hoverOverElement() {
        var action = new Actions(driver);
        waitForElementMiddleTime();
        action.moveToElement(element).perform();
    }
    
    public void waitForElementMiddleTime() {
        waitForElement(middleWaiterOfElement);
    }
    
    public void waitForElementMiddleTime(BaseElement elementName) {
        waitForElement(middleWaiterOfElement, elementName);
    }
    
    public void click() {
        waitForElementMiddleTime();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        hoverOverElement();
        element.click();
    }
    
    public void waitForPageToLoad() {
        middleWaiterOfElement.until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            String readyState = (String) ((JavascriptExecutor) webDriver).executeScript("return document.readyState");
            return "complete".equals(readyState);
        });
    }
    
    public void clickAndWait() {
        click();
        waitForPageToLoad();
    }
    
}