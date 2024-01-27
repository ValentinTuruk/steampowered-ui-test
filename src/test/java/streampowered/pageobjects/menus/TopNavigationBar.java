package streampowered.pageobjects.menus;

import framework.BaseFragment;
import framework.elements.Button;
import framework.elements.List;
import framework.helpers.LanguageManager;
import streampowered.optionnames.LanguageNames;

import java.util.Objects;

public class TopNavigationBar extends BaseFragment {
    Button btnLanguage = new Button("//span[@id='language_pulldown']");
    List lstLanguage = new List("//div[@id='language_dropdown']//a[contains(@class, 'popup_menu_item') and contains(text(),'%s')]");
    
    public void setLanguageOption(String language) {
        if (!Objects.equals(LanguageNames.valueOf(language).getLanguageAbbreviation(), LanguageManager.getCurrentLanguage())) {
            btnLanguage.click();
            lstLanguage.setLanguageOption(language);
        }
    }
    
    
}
