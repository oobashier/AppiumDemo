import com.shaft.driver.SHAFT;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import java.net.MalformedURLException;
import java.net.URL;
import static org.testng.AssertJUnit.assertTrue;

public class LoginTests {
    WebDriver driver;
    SHAFT.TestData.JSON testData;
    LoginPage loginPage;


    @Test(testName = "TC#1",
            description = "Login with Valid Email and password and " +
                    "validate that login is performed successfully by validate we are landing in products page "
    )
    public void testLoginSuccessfully() {
        String pageTitle = loginPage.
                           login(testData.getTestData("credentials.validUserName"), testData.getTestData("credentials.validPassword"))
                           .getPageTitle();

        assertTrue(pageTitle.equalsIgnoreCase("products"));
    }

    @Test(testName = "TC#2",
            description = "Login with invalid email or password and validate that error message is appeared")
    public void testFailedLogin() {

        loginPage.login(testData.getTestData("credentials.inValidUserName"), testData.getTestData("credentials.inValidPassword"));
        loginPage.validate_errorMessage(testData.getTestData("messages.errorLoginMessage"));

    }



    @BeforeMethod
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:deviceName", "realme 6");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "12");
        caps.setCapability("appium:automationName", "UiAutomator2");
        testData = new SHAFT.TestData.JSON("TestData.json");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
