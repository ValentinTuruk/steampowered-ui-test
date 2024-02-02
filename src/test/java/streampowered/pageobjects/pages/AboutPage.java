package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Label;
import framework.utils.DownloadMaster;

public final class AboutPage extends BasePage {
    private static String xpathHeader = "//div[@id='about_header']";
    
    public AboutPage() {
        super(xpathHeader);
    }
    
    Label lblInstallSteam = new Label("//div[@id='about_header']//a[contains(@class, 'about_install_steam_link')]");
    
    public void installSteam() {
        lblInstallSteam.clickAndWait();
        DownloadMaster.verifyFileDownloaded(getConfigProperty("downloadFile.name"), getConfigProperty("downloadFile.extension"));
    }
    
}
