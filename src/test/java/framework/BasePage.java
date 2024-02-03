package framework;

import framework.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static framework.utils.PropertiesReader.getConfigProperty;

public abstract class BasePage {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    
    public BasePage(String xpathPrimaryElement) {
        this.driver = Setup.driver;
        this.softAssert = new SoftAssert();
        BaseElement.checkBasePageElement(xpathPrimaryElement);
    }
    
    public boolean checkAgeControlPageOpened() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains(getConfigProperty("ageControl.urlPart"));
    }
    
    public void checkSoftAsserts() {
        this.softAssert.assertAll();
    }
}
