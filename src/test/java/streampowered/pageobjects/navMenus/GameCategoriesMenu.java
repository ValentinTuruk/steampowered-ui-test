package streampowered.pageobjects.navMenus;

import framework.BaseFragment;
import framework.Browser;
import framework.elements.Label;
import framework.elements.ListOfElements;
import framework.helpers.CommonFunctions;
import streampowered.SharedData;

import java.util.ArrayList;
import java.util.List;

import static framework.helpers.CommonFunctions.formatString;

public class GameCategoriesMenu extends BaseFragment {
    private final int ITEMS_NUMBER = 12;
    
    private Label lblCategories = new Label("//div[contains(@class, 'Focusable') and contains (text(), '%s')]");
    private ListOfElements lstDiscounts = new ListOfElements("//div[contains(@class, 'sale_item')]//div[contains(@class, 'StoreSaleDiscountBox')]");
    private ListOfElements lstGameWidget = new ListOfElements("(//div[contains(@class, 'sale_item')]//div[contains(@class, 'salepreviewwidgets_StoreSaleWidgetHalfLeft')])[%s]");
    private ListOfElements lstGameTitles = new ListOfElements("(//div[contains(@class, 'sale_item')]//div[contains(@class, 'salepreviewwidgets_Title')])[%s]/a");
    
    public void navigateToCategory(String categoryName) {
        lblCategories.scrollToCenter();
        lblCategories.click(categoryName);
    }
    
    public void selectMaxDiscountGame() {
        Browser.refreshPage();
        lstDiscounts.waitForListOfElementsMiddleTime(ITEMS_NUMBER);
        int maxDiscount = 0;
        List<Integer> elementIndexes = new ArrayList<>();
        for (int i = 0; i < lstDiscounts.elements.size(); i++) {
            var elementText = lstDiscounts.elements.get(i).getText();
            int discount = CommonFunctions.getTwoDigitsBeforeProcent(elementText);
            if (discount > maxDiscount) {
                maxDiscount = discount;
                elementIndexes.clear();
                elementIndexes.add(i);
            } else if (discount == maxDiscount) {
                elementIndexes.add(i);
            }
        }
        int indexOfItem = lstDiscounts.getRandomElementFromList(elementIndexes);

        lstGameTitles.waitForElementFromListMiddleTime(indexOfItem + 1);
        SharedData.setNameOfGame(formatString(lstGameTitles.getElementText()));
        lstGameWidget.clickAndWait(indexOfItem + 1);
    }
}
