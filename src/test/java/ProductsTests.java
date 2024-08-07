import com.shaft.driver.SHAFT;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.AssertJUnit.assertTrue;

public class ProductsTests {

    WebDriver driver;
    SHAFT.TestData.JSON testData;

    ///////////////Pages
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;


    @Test(testName = "TC#3",
            description = "Add Any Item to the cart and validate that Title and price of the Item at Home page equals the item and price at the cart")
    public void validateTheItemDetailsIsSameInProductAndCartPages() {
        String itemTile = productsPage.getItemTitle();
        String itemPrice = productsPage.getItemPrice();

        productsPage.addItemToTheCart()
                    .validateAddingTheItemToCart("1")
                    .navigateTOCart();

        assertTrue(itemTile.equalsIgnoreCase(cartPage.getItemTitle()));
        assertTrue(itemPrice.equalsIgnoreCase(cartPage.getItemPrice()));
    }


    @Test(testName = "TC#4",
            description = "Validate Removing Items from the cart and validate that the cart is empty")
    public void validateRemovingItemFromCart() {
        productsPage.addItemToTheCart()
                    .navigateTOCart()
                    .removeItemFromTheCart()
                    .validateTheItemIsRemoved();
    }

    @Test(testName = "TC#5", description = "Validate the total flow of Online Ordering and complete the flow from adding element to cart till the checkout," +
            " Also Validate the total price and success purchase message.")
    public void ValidateTheTotalFlowOfOnlineOrdering() {
        productsPage.addItemToTheCart()
                .navigateTOCart()
                .clickCheckOutButton()
                .enterCheckOutInfo(testData.getTestData("checkOutInformation.firstName"),
                                   testData.getTestData("checkOutInformation.lastName"),
                                   testData.getTestData("checkOutInformation.postalCode"))
                .validateTotalCostIsTaxPlusTotal()
                .clickFinishCheckOut()
                .validateSuccessMessage(testData.getTestData("messages.successPurchaseMessage"));
    }


    @BeforeMethod
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:deviceName", "realme 6");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        testData = new SHAFT.TestData.JSON("TestData.json");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
        loginPage = new LoginPage(driver);
        loginPage.login(testData.getTestData("credentials.validUserName"), testData.getTestData("credentials.validPassword"));
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
