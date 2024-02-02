package framework.elements;

import org.openqa.selenium.support.ui.Select;

public final class Dropdown extends BaseElement {
    public Dropdown(String xpathOfDropdown) {
        super(xpathOfDropdown);
    }
    
    public void setValue(String value) {
        hoverOverElement();
        var dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }
    
}
