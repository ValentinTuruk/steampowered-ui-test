package streampowered.tests;

import framework.Browser;
import framework.Setup;
import org.testng.annotations.Test;
import streampowered.pageobjects.pages.HomePage;

public class SaleGameTest extends Setup {
    
    @Test
    public void maxDiscountTest() {
        Browser.navigateTo("base.url");
        var homePage = new HomePage();
        homePage.getTopNavigationBar().setLanguageOption("ENGLISH");
    }
}