package streampowered.pageobjects.fragments;

import framework.BaseFragment;
import framework.elements.ListByName;

public class NavigationMenu extends BaseFragment {
    ListByName lstCategories = new ListByName("//a[contains(@class, 'pulldown_desktop') and contains(text(),'%s')]");
    ListByName lstDepartments = new ListByName("//div[@id='store_nav_area']//a[@class='popup_menu_item' and text()[normalize-space() = '%s']]");
    
    public void navigateToDepartment(String categoryName, String departmentName) {
        lstCategories.hoverOverElement(categoryName);
        lstDepartments.clickAndWait(departmentName);
    }
    
}
