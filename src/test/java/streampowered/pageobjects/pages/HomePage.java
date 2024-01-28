package streampowered.pageobjects.pages;

import framework.BasePage;
import streampowered.pageobjects.menus.NavigationMenu;
import streampowered.pageobjects.menus.TopBarMenu;

public class HomePage extends BasePage {
    private static String xpathWrapper = "//div[@class='home_page_col_wrapper']";
    private TopBarMenu topBarMenu;
    private NavigationMenu navigationMenu;
    
    public HomePage() {
        super(xpathWrapper);
        this.topBarMenu = new TopBarMenu();
        this.navigationMenu = new NavigationMenu();
    }
    
    public TopBarMenu getTopBarMenu() {
        return topBarMenu;
    }
    
    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }
    
    
}
