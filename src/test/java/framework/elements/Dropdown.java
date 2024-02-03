package framework.elements;

import framework.helpers.LanguageManager;
import org.openqa.selenium.support.ui.Select;

public final class Dropdown extends BaseElement {
    public Dropdown(String xpathOfDropdown) {
        super(xpathOfDropdown);
    }
    
    public void setValue(String value) {
        hoverOverElement();
        var dropdown = new Select(element);
        value = LanguageManager.getLocalName(value);
        dropdown.selectByVisibleText(value);
    }
}
