package streampowered.pageobjects.menus;

import framework.BaseFragment;
import framework.elements.Button;
import framework.elements.ListOfElements;
import framework.helpers.LanguageManager;

import java.util.Objects;


public class TopBarMenu extends BaseFragment {
    Button btnLanguage = new Button("//span[@id='language_pulldown']");
    ListOfElements lstLanguages = new ListOfElements("//div[@id='language_dropdown']//a[contains(@class, 'popup_menu_item') and contains(text(),'%s')]");
    
    public void setLanguage(String language) {
        if (!Objects.equals(LanguageManager.getOptionCurrentLocal(String.format("%s.abbreviation",language).toLowerCase()), LanguageManager.getCurrentLanguage())) {
            btnLanguage.click();
            lstLanguages.setLanguageOption(language);
        }
    }
    
    
}
