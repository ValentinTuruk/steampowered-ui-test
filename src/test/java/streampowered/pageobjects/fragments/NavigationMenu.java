package streampowered.pageobjects.fragments;

import framework.BaseFragment;
import framework.elements.Label;

public class NavigationMenu extends BaseFragment {
    private Label lblCategories = new Label("//a[contains(@class, 'pulldown_desktop') and contains(text(),'%s')]");
    private Label lblDepartments = new Label("//div[@id='store_nav_area']//a[@class='popup_menu_item' and text()[normalize-space() = '%s']]");
    
    public void navigateToDepartment(String categoryName, String departmentName) {
        lblCategories.hoverOverElement(categoryName);
        lblDepartments.clickAndWait(departmentName);
    }
    
}
