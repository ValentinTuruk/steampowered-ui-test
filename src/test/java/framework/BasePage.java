package framework;

import framework.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class BasePage {
    public BasePage(String xpathPrimaryElement) {
        this.driver = Setup.driver;
        this.softAssert = new SoftAssert();
        BaseElement.checkBaseElement(xpathPrimaryElement);
    }
    
    protected WebDriver driver;
    protected SoftAssert softAssert;
    
    public void checkSoftAsserts() {
        this.softAssert.assertAll();
    }
}
