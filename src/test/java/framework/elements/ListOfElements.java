package framework.elements;

import framework.Browser;
import framework.helpers.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ListOfElements extends BaseElement {
    public ListOfElements(String xpathOfListNoName) {
        super(xpathOfListNoName);
    }
    
    public List<WebElement> elements;
    
    // ---------------LIST ACTIONS---------------
    private void waitForListOfElements(WebDriverWait wait, int elementsNumber) {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfElement), elementsNumber));
        elements = driver.findElements(By.xpath(xpathOfElement));
        scrollListOfElementsIntoView();
    }
    
    public void waitForListOfElementsMiddleTime(int elementsNumber) {
        waitForListOfElements(getMiddleWaiterOfElement(), elementsNumber);
    }
    
    private void scrollListOfElementsIntoView() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", elements.get(elements.size() / 2));
    }
    
    public int getRandomElementFromList(List<Integer> listOfElements) {
        Random random = new Random();
        return listOfElements.get(random.nextInt(listOfElements.size()));
    }
    
    // ---------------ELEMENT FROM LIST ACTIONS---------------
    public void clickAndWait(int number) {
        click(number);
        Browser.waitForPageToLoad();
    }
    
    public void click(int number) {
        hoverOverElement(number);
        element.click();
    }
    
    public void hoverOverElement(int number) {
        var action = new Actions(driver);
        waitForElementFromListMiddleTime(number);
        super.scrollIntoView();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    private void waitForElementFromList(WebDriverWait wait, int number) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, number))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, number)));
    }
    
    public void waitForElementFromListMiddleTime(int number) {
        waitForElementFromList(getMiddleWaiterOfElement(), number);
    }
    
    public String getElementText() {
        return element.getText();
    }
}
