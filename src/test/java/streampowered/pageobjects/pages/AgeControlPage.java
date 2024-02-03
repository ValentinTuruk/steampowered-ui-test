package streampowered.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Dropdown;

public final class AgeControlPage extends BasePage {
    private static String xpathAgeGate = "//div[@id='app_agegate']";
    
    public AgeControlPage() {
        super(xpathAgeGate);
    }
    
    private Dropdown ddwDay = new Dropdown("//select[@id='ageDay']");
    private Dropdown ddwMonth = new Dropdown("//select[@id='ageMonth']");
    private Dropdown ddwYear = new Dropdown("//select[@id='ageYear']");
    private Button btnViewProductPage = new Button("//a[@id='view_product_page_btn']");
    
    public void setPositiveAge() {
        ddwDay.setValue("ageControlPage.day");
        ddwMonth.setValue("ageControlPage.month");
        ddwYear.setValue("ageControlPage.year");
        btnViewProductPage.clickAndWait();
    }
    
    
}
