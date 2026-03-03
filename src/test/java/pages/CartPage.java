
package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    @FindBy(xpath = "//span[@class='title' and text()='Your Cart']")
    private WebElement yourCartHeader;

    // All cart line items
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;


    // Directly list all product names in cart
    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> cartItemNames;


    public boolean isAt() {
        return "Your Cart".equals(getText(yourCartHeader));
    }

    /**
     * Read all product names present in cart
     */

    public List<String> getCartProductNames() {
        return cartItemNames.stream()
                .map(this::getText)
                .toList();

    }
}
