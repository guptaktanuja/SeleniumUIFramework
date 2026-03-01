
package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {


    @FindBy(xpath = "//*[@data-test='title']")
    private WebElement inventoryPageTitle;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;


    // All product tiles
    @FindBy(css = ".inventory_item")
    private List<WebElement> productTiles;

    // Inside each product tile:
    // .inventory_item_name  -> name element
    // .btn_inventory        -> add/remove button

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;


    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;



    public String getInventoryPageTitle()
    {
        return getText(inventoryPageTitle);
    }

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public InventoryPage sortByPriceLowToHigh() {
        // You can use Select or click the dropdown and choose by visible text
        Select select =new Select(sortDropdown);

        select.selectByVisibleText("Price (low to high)");
        return this;
    }



    public List<String> getProductNames() {
        // BasePage.getText(WebElement) already waits for visibility
        return productNames.stream().map(this::getText).collect(Collectors.toList());
    }


    public String addProductByIndex(int index) {
        String productName = getText(productNames.get(index));
        click(addToCartButtons.get(index));   // BasePage.click(WebElement)
        return productName;
    }

    /** Go to cart page */
    public CartPage openCart() {
        click(cartLink);
        return new CartPage();
    }



}
