
package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {


    @FindBy(name = "q")
    private WebElement searchBox;

   /* public void type(String text) {
        type(searchBox, text);
    }
}*/
}

