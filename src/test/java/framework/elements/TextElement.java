package framework.elements;

public final class TextElement extends BaseElement {
    public TextElement(String xpathOfText) {
        super(xpathOfText);
    }
    
    public String getElementText() {
        waitForElementMiddleTime();
        return element.getText();
    }
}
