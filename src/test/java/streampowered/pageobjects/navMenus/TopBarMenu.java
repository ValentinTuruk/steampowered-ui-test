package streampowered.pageobjects.navMenus;

import framework.BaseFragment;
import framework.elements.Button;
import framework.elements.Label;
import framework.helpers.LanguageManager;

import java.util.Objects;

import static framework.helpers.CommonFunctions.formatString;
import static framework.utils.PropertiesReader.getConfigProperty;

public class TopBarMenu extends BaseFragment {
    private Button btnLanguage = new Button("//span[@id='language_pulldown']");
    private Label lblLanguages = new Label("//div[@id='language_dropdown']//a[contains(@class, 'popup_menu_item') and contains(@onclick,'%s')]");
    
    public void setLanguage(String languageCode) {
        languageCode = formatString(languageCode);
        if (!Objects.equals(languageCode, LanguageManager.getCurrentLanguage())) {
            btnLanguage.click();
            lblLanguages.clickByAttribute(getConfigProperty(languageCode));
            LanguageManager.waitAndSaveLanguage(languageCode);
        }
    }
}
