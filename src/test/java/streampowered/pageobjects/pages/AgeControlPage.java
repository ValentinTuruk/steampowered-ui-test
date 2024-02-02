package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Dropdown;

public final class AgeControlPage extends BasePage {
    private static String xpathAgeGate = "//div[@id='app_agegate']";
    
    public AgeControlPage() {
        super(xpathAgeGate);
    }
    
    Dropdown ddwDay = new Dropdown("//select[@id='ageDay']");
    Dropdown ddwMonth = new Dropdown("//select[@id='ageMonth']");
    Dropdown ddwYear = new Dropdown("//select[@id='ageYear']");
    Button btnViewProductPage = new Button("//a[@id='view_product_page_btn']");
    
    public void setPosiriveAge() {
        ddwDay.setValue(getConfigProperty("ageControl.day"));
        ddwMonth.setValue(getConfigProperty("ageControl.month"));
        ddwYear.setValue(getConfigProperty("ageControl.year"));
        btnViewProductPage.clickAndWait();
    }
    
    
}
