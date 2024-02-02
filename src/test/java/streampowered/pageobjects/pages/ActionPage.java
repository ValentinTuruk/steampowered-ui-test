package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.helpers.LanguageManager;
import streampowered.pageobjects.fragments.SaleItamsFragment;

public final class ActionPage extends BasePage {
    private static String xpathPageTitle = String.format("//div[contains(@class,'ContentHubTitle') and text()='%s']", LanguageManager.getLocalName("pageTitle.action"));
    private SaleItamsFragment saleItamsFragment;
    
    public ActionPage() {
        super(xpathPageTitle);
        this.saleItamsFragment = new SaleItamsFragment();
    }
    
    public SaleItamsFragment getSaleItamsFragment() {
        return saleItamsFragment;
    }
    
    
}
