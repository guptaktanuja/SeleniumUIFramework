package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E2ETest extends BaseTest {


    @Test
    public void addOneItem_andVerifyInCart_usingHashMap() {
        Map<String, String> expectedProductNames = new HashMap<>();

        InventoryPage inventory = new LoginPage()
                .open()
                .loginAs("standard_user", "secret_sauce");

       
        String addedName = inventory.addProductByIndex(0); // uses @FindBy lists or component
        expectedProductNames.put("1", addedName);

        CartPage cart = inventory.openCart();
        Assert.assertTrue(cart.isAt(), "Not on Cart page");

        List<String> actualCartNames = cart.getCartProductNames();
        Assert.assertFalse(actualCartNames.isEmpty(), "Cart is empty after adding an item");

        Assert.assertEquals(actualCartNames.get(0), expectedProductNames.get("1"),
                "Cart item name mismatch with expectedProductNames map value");
    }

}
