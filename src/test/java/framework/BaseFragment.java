package framework;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public abstract class BaseFragment {
    public BaseFragment() {
        this.driver = Setup.driver;
        this.softAssert = new SoftAssert();
    }
    
    protected WebDriver driver;
    protected SoftAssert softAssert;
    
    public void checkSoftAsserts() {
        softAssert.assertAll();
    }
}
