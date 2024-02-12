package streampowered.pageobjects.pages;

import framework.BasePage;
import streampowered.pageobjects.navMenus.GameCategoriesMenu;

import static framework.helpers.LanguageManager.getLocalName;

public final class ActionPage extends BasePage {
    private static String xpathPageTitle = String.format("//div[contains(@class,'ContentHubTitle') and text()='%s']", getLocalName("pageTitle.action"));
    private GameCategoriesMenu gameCategoriesMenu;
    
    public ActionPage() {
        super(xpathPageTitle);
        this.gameCategoriesMenu = new GameCategoriesMenu();
    }
    
    public GameCategoriesMenu getGameCategoriesMenu() {
        return gameCategoriesMenu;
    }
}
