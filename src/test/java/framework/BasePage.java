package framework;

import framework.elements.BaseElement;
import framework.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public abstract class BasePage {
    public BasePage(String xpathPrimaryElement) {
        this.driver = Setup.driver;
        this.softAssert = new SoftAssert();
        BaseElement.checkBaseElement(xpathPrimaryElement);
    }
    
    protected WebDriver driver;
    protected SoftAssert softAssert;
    
    public boolean checkAgeControlPageOpened() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains(getConfigProperty("ageControl.urlPart"));
    }
    
    public void checkSoftAsserts() {
        this.softAssert.assertAll();
    }
    
    public String getConfigProperty(String key) {
        return PropertiesReader.getProperty("config", key);
    }
}
