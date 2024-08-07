package pages;

import Actions.ElementsActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertEquals;

public class CheckOutCompletePage {
    WebDriver driver;

    /////////////Locators
    private By successMessage = By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]");

   ///////////Constructor
    public CheckOutCompletePage(WebDriver driver) {
        this.driver= driver;
    }


    ///////////////Validations
    /**
     * this method is to assert on success purchase message
     * @param expectedMessage get from json file
     */
    public void validateSuccessMessage(String expectedMessage)
    {
        assertEquals(expectedMessage, ElementsActions.getText(driver,successMessage));
    }
}
