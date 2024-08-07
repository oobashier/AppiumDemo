package pages;

import Actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertEquals;

public class CartPage {

    WebDriver driver;

    /////////////////Locators

    private By item_Title = By.xpath("//android.widget.TextView[@text='Sauce Labs Backpack']");
    private By item_Price = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    private By remove_Button = AppiumBy.accessibilityId("test-REMOVE");
    private By itemsOnTheCart = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]");
    private By checkOut_Button = AppiumBy.accessibilityId("test-CHECKOUT");

    /////// Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //////////////Actions

    public String getItemTitle() {
        return ElementsActions.getText(driver, item_Title);
    }

    public String getItemPrice() {
        return ElementsActions.getText(driver, item_Price);
    }


    public CartPage removeItemFromTheCart() {
        ElementsActions.click(driver, remove_Button);
        return this;
    }


    /**
     * this method is used to click checkOut button then navigate to checkOut page.
     * @return
     */
    public CheckOutPage clickCheckOutButton()
    {
        ElementsActions.click(driver,checkOut_Button);
        return new CheckOutPage(driver);
    }


    /**
     * this method returns the number of items on the cart ,
     * so i will use it to validate the item is removed from the cart and the cart is empty
     *
     * @return number of items in the cart
     */
    private int getNumOfItemsOnTheCart() {
        return driver.findElements(itemsOnTheCart).size();
    }


    ////////////Validations

    /**
     * this method is used to validate the item is removed from the cart and the cart is empty.
     */
    public void validateTheItemIsRemoved() {
        assertEquals(0, getNumOfItemsOnTheCart());
    }

}
