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

public final class ListNoName extends BaseElement {
    public ListNoName(String xpathOfListNoName) {
        super(xpathOfListNoName);
    }
    
    protected List<WebElement> elements;
    
    private void waitForElement(WebDriverWait wait, int elementsNumber) {
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfElement), elementsNumber));
        elements = driver.findElements(By.xpath(xpathOfElement));
        scrollIntoView();
    }
    
    public void waitForElementMiddleTime(int elementsNumber) {
        waitForElement(middleWaiterOfElement, elementsNumber);
    }
    
    public int getMaxDiscountElementIndex(int elementsNumber) {
        Browser.refreshPage();
        waitForElementMiddleTime(elementsNumber);
        int maxDiscount = 0;
        List<Integer> elementIndexes = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            var elementText = elements.get(i).getText();
            int discount = CommonFunctions.getTwoDigitsBeforeProcent(elementText);
            if (discount > maxDiscount) {
                maxDiscount = discount;
                elementIndexes.clear();
                elementIndexes.add(i);
            } else if (discount == maxDiscount) {
                elementIndexes.add(i);
            }
        }
        return CommonFunctions.getRandomElementOfList(elementIndexes);
    }
    
    public void scrollIntoView() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", elements.get(5));
    }
    
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
        waitForOneOfElementsMiddleTime(number);
        super.scrollIntoView();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid red'", element);
        }
        action.moveToElement(element).perform();
    }
    
    private void waitForElementOfElements(WebDriverWait wait, int number) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpathOfElement, number))));
        element = driver.findElement(By.xpath(String.format(xpathOfElement, number)));
    }
    
    public void waitForOneOfElementsMiddleTime(int number) {
        waitForElementOfElements(middleWaiterOfElement, number);
    }
    
}
