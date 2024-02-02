package streampowered.pageobjects.fragments;

import framework.BaseFragment;
import framework.elements.ListByName;
import framework.elements.ListNoName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import streampowered.SharedData;

public class SaleItamsFragment extends BaseFragment {
    private final int ITEMS_NUMBER = 12;
    
    ListByName lstCategories = new ListByName("//div[contains(@class, 'saleitembrowser') and contains (text(), '%s')]");
    ListNoName lnnDiscounts = new ListNoName("//div[contains(@class, 'sale_item')]//div[contains(@class, 'StoreSaleDiscountBox')]");
    //    ListByName lstTitle = new ListByName("(//div[contains(@class, 'sale_item')]//div[contains(@class, 'salepreviewwidgets_Title')])[%s]/a");
    ListNoName lblGameWidget = new ListNoName("(//div[contains(@class, 'sale_item')]//div[contains(@class, 'salepreviewwidgets_StoreSaleWidgetHalfLeft')])[%s]");
    
    
    public void navigateToCategory(String categoryName) {
        lstCategories.scrollAndClick(categoryName);
    }
    
    public void selectMaxDiscountGame() {
        int indexOfItem = lnnDiscounts.getMaxDiscountElementIndex(ITEMS_NUMBER);
        WebElement gameTitle = driver.findElement(By.xpath(String.format("(//div[contains(@class, 'sale_item')]//div[contains(@class, 'salepreviewwidgets_Title')])[%s]/a", indexOfItem + 1)));
        lblGameWidget.clickAndWait(indexOfItem + 1);
        SharedData.setNameOfGame(gameTitle.getText().trim().toLowerCase());
    }
}
