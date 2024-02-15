package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import framework.utils.SoftAsserts;
import streampowered.SharedData;

import static framework.helpers.CommonFunctions.formatString;

public final class GamePage extends BasePage {
    private static String xpathBackground = "//div[contains(@class,'game_page_background')]";
    
    public GamePage() {
        super(xpathBackground);
    }
    
    private TextBox txtTitle = new TextBox("//div[@id='appHubAppName']");
    private Button btnInstallSteam = new Button("//a[contains(@class,'header_installsteam_btn header')]");
    
    public void navigateToInstallSteamPage() {
        btnInstallSteam.clickAndWait();
    }
    
    public void verifyCorrectGamePage() {
        var actualNameOfGame = formatString(txtTitle.getElementText());
        SoftAsserts.softAssert.assertEquals(actualNameOfGame, SharedData.getNameOfGame());
    }
}
