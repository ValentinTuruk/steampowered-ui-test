package streampowered.tests;

import framework.Browser;
import framework.Setup;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import streampowered.pageobjects.pages.*;

public class SaleGameTest extends Setup {
    String language;
    
    @Parameters({"language"})
    @BeforeTest
    public void setLanguage(String language) {
        this.language = language;
    }
    
    @Test
    public void maxDiscountTest() {
        Browser.navigateTo("base.url");
        
        var homePage = new HomePage();
        homePage.getTopBarMenu().setLanguage(language);
        homePage.getNavigationMenu().navigateToDepartment("navigationMenu.categories", "navigationMenuDepartment.action");
        
        var actionPage = new ActionPage();
        actionPage.getGameCategoriesMenu().navigateToCategory("saleItemsFragment.discounted");
        actionPage.getGameCategoriesMenu().selectMaxDiscountGame();
        
        Browser.switchTab();
        if (actionPage.checkAgeControlPageOpened()) {
            var ageControlPage = new AgeControlPage();
            ageControlPage.setPositiveAge();
        }
        
        var gamePage = new GamePage();
        gamePage.verifyCorrectGamePage();
        gamePage.navigateToInstallSteamPage();
        
        var installSteamPage = new InstallSteamPage();
        installSteamPage.downloadAndVerifySteamSetupFile();
    }
}
