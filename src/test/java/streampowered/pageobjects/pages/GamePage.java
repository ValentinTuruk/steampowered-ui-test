package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Text;
import streampowered.SharedData;

public final class GamePage extends BasePage {
    private static String xpathBackground = "//div[contains(@class,'game_page_background')]";
    
    public GamePage() {
        super(xpathBackground);
    }
    
    Text txtTitle = new Text("//div[@id='appHubAppName']");
    Button btnInstallSteam = new Button("//a[contains(@class,'header_installsteam_btn header')]");
    
    public void installSteam() {
        btnInstallSteam.clickAndWait();
    }
    
    public void verifyCorrectGamePage() {
        var actualNameOfGame = txtTitle.getElenetText().trim().toLowerCase();
        softAssert.assertEquals(actualNameOfGame, SharedData.getNameOfGame());
    }
}
