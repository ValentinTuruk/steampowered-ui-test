package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Label;
import framework.utils.DownloadMaster;

import static framework.utils.PropertiesReader.getConfigProperty;

public final class InstallSteamPage extends BasePage {
    private static String xpathHeader = "//div[@id='about_header']";
    
    public InstallSteamPage() {
        super(xpathHeader);
    }
    
    private Label lblInstallSteam = new Label("//div[@id='about_header']//a[contains(@class, 'about_install_steam_link')]");
    
    public void installSteam() {
        lblInstallSteam.clickAndWait();
        var fileName = getConfigProperty("downloadFile.name");
        var fileExtension = getConfigProperty("downloadFile.extension");
        DownloadMaster.verifyFileDownloaded(fileName, fileExtension);
    }
    
}
