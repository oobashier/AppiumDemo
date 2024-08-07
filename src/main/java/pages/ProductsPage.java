package pages;

import Actions.ElementsActions;
import com.shaft.gui.element.ElementActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ProductsPage {

    WebDriver driver;


    //////////////Locators
    private By page_Title = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    private By item_Title = By.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]");
    private By item_Price = By.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[1]");
    private By addToCart_Button = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]");
    private By removeFromCart_Button = AppiumBy.accessibilityId("test-REMOVE");
    private By shoppingCart_Badge = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");


    /////////Constructors
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }



    ////////////Actions

    public String getPageTitle()
    {
       return ElementsActions.getText(driver,page_Title);
    }

    public ProductsPage addItemToTheCart() {
        ElementsActions.click(driver, addToCart_Button);
        return this;
    }

    public CartPage navigateTOCart()
    {
        ElementsActions.click(driver, shoppingCart_Badge);
        return new CartPage(driver);
    }

    /**
     * this method is used to get the title of the product to assert on it.
     * @return
     */

    public String getItemTitle() {
        return ElementsActions.getText(driver, item_Title);
    }

    /**
     * this method is used to get the price of the product to assert on it.
     * @return
     */
    public String getItemPrice() {
        return ElementsActions.getText(driver, item_Price);
    }



    /////////////Validations

    /**
     * this method to validate that the items id added to cart by checking
     * 1. the addToCart button is changed to remove button
     * 2. the num of selected products reflected to the cart on the page
     *
     * @param numOfProductsInCart the number of items you added to the cart
     */
    public ProductsPage validateAddingTheItemToCart(String numOfProductsInCart) {
        String noOfProductsInCart = driver.findElement(shoppingCart_Badge).getAttribute("text");
        assertTrue(noOfProductsInCart.equals(numOfProductsInCart));
        assertTrue(removeFromCart_Button.toString().contains("REMOVE"));
        return this;
    }


}
