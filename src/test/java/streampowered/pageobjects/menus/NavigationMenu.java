package streampowered.pageobjects.menus;

import framework.BaseFragment;
import framework.elements.ListOfElements;

public class NavigationMenu extends BaseFragment {
    ListOfElements lstCategories = new ListOfElements("//a[contains(@class, 'pulldown_desktop') and contains(text(),'%s')]");
    ListOfElements lstDepartments = new ListOfElements("//div[@id='store_nav_area']//a[@class='popup_menu_item' and text()[normalize-space() = '%s']]");
    
    public void navigateToDepartment (String categoryName, String departmentName) {
        lstCategories.hoverOverElement(categoryName);
        lstDepartments.clickAndWait(departmentName);
    }
    
}
