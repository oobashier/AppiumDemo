package pages;

import Actions.ElementsActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.AssertJUnit.assertEquals;


public class LoginPage {
    WebDriver driver;

    //////////////////////////////Locators
    private By userName_Field = AppiumBy.accessibilityId("test-Username");
    private By password_Field = AppiumBy.accessibilityId("test-Password");
    private By login_Button = AppiumBy.accessibilityId("test-LOGIN");
    private By error_Message = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");


    //////////////Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }



    /////////////Actions

    /**
     * this method is used to log in using userName and password then click login button
     * @param userName
     * @param password
     * @return instance of ProductsPage
     */
    public ProductsPage login(String userName, String password) {
        ElementsActions.setText(driver, userName_Field, userName,true);
        ElementsActions.setText(driver, password_Field, password,true);
        ElementsActions.click(driver, login_Button);
        return new ProductsPage(driver);
    }



    /////////////Validations

    /**
     * this method is used to assert on error message when entering invalid credentials
     * @param expectedErrorMessage
     */
    public void validate_errorMessage(String expectedErrorMessage) {
        assertEquals(ElementsActions.getText(driver, error_Message), expectedErrorMessage);
    }


}
