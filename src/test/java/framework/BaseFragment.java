package framework;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public abstract class BaseFragment {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    
    public BaseFragment() {
        this.driver = Setup.driver;
        this.softAssert = new SoftAssert();
    }
    
    public void checkSoftAsserts() {
        softAssert.assertAll();
    }
}
