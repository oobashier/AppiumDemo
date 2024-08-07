package pages;

import Actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    WebDriver driver;

    //////////////////////////////Locators
    private By firstName_Field = AppiumBy.accessibilityId("test-First Name");
    private By lastName_Field = AppiumBy.accessibilityId("test-Last Name");
    private By postalCode_Field= AppiumBy.accessibilityId("test-Zip/Postal Code");
    private By continue_Button = AppiumBy.accessibilityId("test-CONTINUE");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    ///////////////////////////Actions

    /**
     * this method is used to set checkOut information
     * @param firstName
     * @param lastName
     * @param postalCode
     * @return
     */
    public CheckOutOverviewPage enterCheckOutInfo(String firstName, String lastName , String postalCode)
    {
        ElementsActions.setText(driver,firstName_Field,firstName,true);
        ElementsActions.setText(driver,lastName_Field,lastName,true);
        ElementsActions.setText(driver,postalCode_Field,postalCode,true);
        ElementsActions.click(driver,continue_Button);
        return new CheckOutOverviewPage(driver);
    }

}
