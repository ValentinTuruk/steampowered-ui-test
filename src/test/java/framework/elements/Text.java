package framework.elements;

public final class Text extends BaseElement {
    public Text(String xpathOfText) {
        super(xpathOfText);
    }
    
    public String getElenetText() {
        waitForElementMiddleTime();
        return element.getText();
    }
}
