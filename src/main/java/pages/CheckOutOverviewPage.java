package pages;

import Actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertTrue;

public class CheckOutOverviewPage {

    WebDriver driver;

    ////////////Locators
    private By itemPrice = By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[3]");
    private By taxCost = By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[4]");
    private By totalPrice = By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[5]");
    private By finish_Button = AppiumBy.accessibilityId("test-FINISH");


    ///////////// Constructor
    public CheckOutOverviewPage(WebDriver driver) {
        this.driver= driver;
    }

     ///////////////////Actions
     public CheckOutCompletePage clickFinishCheckOut()
     {
         ElementsActions.scrollDownToSpecificText(driver,"FINISH");
         ElementsActions.click(driver,finish_Button);
         return new CheckOutCompletePage(driver);
     }


     /////////////Validations
    /**
     * this method is to validate if the total price equal the itemPrice plus the Tax
     */
    public CheckOutOverviewPage validateTotalCostIsTaxPlusTotal() {

        ElementsActions.scrollDownToSpecificText(driver,"FINISH");

        double itemTotal = Double.parseDouble(driver.findElement(itemPrice).getText().substring(13));
        double tax = Double.parseDouble(driver.findElement(taxCost).getText().substring(6));
        double total = Double.parseDouble(driver.findElement(totalPrice).getText().substring(8));

        assertTrue((itemTotal + tax) == total) ;
        return this;
    }





}
