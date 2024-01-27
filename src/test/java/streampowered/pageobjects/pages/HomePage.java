package streampowered.pageobjects.pages;

import framework.BasePage;
import streampowered.pageobjects.menus.TopNavigationBar;

public class HomePage extends BasePage {
    private static String xpathMainBanner = "//div[@class='home_page_takeover_link']";
    
    public HomePage() {
        super(xpathMainBanner);
        this.topNavigationBar = new TopNavigationBar();
    }

    private TopNavigationBar topNavigationBar;
    
    public TopNavigationBar getTopNavigationBar() {
        return topNavigationBar;
    }
    

}
