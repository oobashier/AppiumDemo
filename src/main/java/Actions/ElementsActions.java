package Actions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementsActions {

    private static WebDriver driver;

    private static WebDriverWait wait ;

    public ElementsActions(WebDriver driver) {
        this.driver = driver;

    }



    /**
     * this method is to wait for the element to be clickable then Click on it
     * @param driver an instance of your driver
     * @param elementLocator the by Element that you want to get its location and click on it
     */
    public static void click(WebDriver driver, By elementLocator) {

        locateTheElement(driver, elementLocator);

        try {
            // wait for the element to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(elementLocator));

        } catch (TimeoutException toe) {
            toe.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
        driver.findElement(elementLocator).click();
    }


    /**
     * this method is to clear the text field then start to write .
     * @param driver an instance of your driver
     * @param elementLocator the by Element that you want to get its location and write on it
     */
    public static void setText(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {

        locateTheElement(driver, elementLocator);

        try {
            // Clear before typing condition
            if (!driver.findElement(elementLocator).getAttribute("text").isBlank() && clearBeforeTyping) {
                driver.findElement(elementLocator).clear();
                // We type here
                driver.findElement(elementLocator).sendKeys(text);

            }else {
                driver.findElement(elementLocator).sendKeys(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}

    /**
     * this method is to get text form web element.
     * @param driver an instance of your driver
     * @param elementLocator the by Element that you want to get its location and get text from it
     */
    public static String getText(WebDriver driver, By elementLocator) {
        locateTheElement(driver, elementLocator);
        try {
            return driver.findElement(elementLocator).getText();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is to scroll down to specific text/Element by passing only the text appeared on the screen
     *
     * @param text the text or element you want to scroll to
     */
    public static void scrollDownToSpecificText(WebDriver driver ,String text) {
//        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
//                + ".instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
        locateTheElement(driver,MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
                + ".instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
    }

    /**
     * This method is to scroll down to a text or Element that containing a text that happened by passing only the text appeared on the screen
     *
     * @param text the text or element you want to scroll to
     */
    public static void scrollDownToSpecificTextContained(WebDriver driver , String text) {
//        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
//                + ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));

        locateTheElement(driver,MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
               + ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }


    /**
     * This a private method the purpose of that is to swipe right
     *
     * @param element the webElement that you want to get its location
     * @param driver  an instance of your driver
     */
    public static void swipeRightOnElement(By element, WebDriver driver) {
        swipeRightOnElement(driver.findElement(element), driver);
    }


    /**
     * This method is to swipe right
     *
     * @param element the by Element that you want to get its location and swipe right out of it
     * @param driver  an instance of your driver
     */
    public static void swipeLeftOnElement(By element,WebDriver driver) {
        swipeLeftOnElement(driver.findElement(element), driver);
    }

    /**
     * this method is to locate the webElements to interact with it
     * @param driver an instance of your driver
     * @param elementLocator the by Element that you want to get its location
     */
    private static void locateTheElement (WebDriver driver, By elementLocator)
        {
            try {
                // Wait for the element to be visible
                wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

                driver.findElement(elementLocator);

            } catch (TimeoutException toe) {
                toe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    /**
     * This a private method the purpose of that is to swipe right
     *
     * @param element the webElement that you want to get its location
     * @param driver  an instance of your driver
     */

    @SuppressWarnings({"rawtypes", "unused"})
    private static void swipeRightOnElement(WebElement element, WebDriver driver) {
        Point point = element.getLocation();
        Dimension eleSize = element.getSize();
        int centerX = driver.manage().window().getSize().width / 2;
        int centerY = point.getY() + (eleSize.getHeight() / 2);
        int moveToX = driver.manage().window().getSize().width / 2;
        int moveToY = point.getY() + (eleSize.getHeight() / 2);

        System.out.println("centerX :" + centerX);
        System.out.println("moveToX :" + centerX * 8 / 5);
        System.out.println("moveToY :" + moveToY);

        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(centerX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(centerX * 8 / 5, moveToY))
                .release().perform();
    }

    /**
     * This a private method the purpose of that is to swipe left
     *
     * @param element the webElement that you want to get its location
     * @param driver  an instance of your driver
     */
    @SuppressWarnings({"rawtypes", "unused"})
    private static void swipeLeftOnElement(WebElement element, WebDriver driver) {
        Point point = element.getLocation();
        Dimension eleSize = element.getSize();
        int centerX = driver.manage().window().getSize().width / 2;
        int centerY = point.getY() + (eleSize.getHeight() / 2);
        int moveToX = driver.manage().window().getSize().width / 2;
        int moveToY = point.getY() + (eleSize.getHeight() / 2);

        System.out.println("centerX :" + centerX);
        System.out.println("moveToX :" + centerX * 1 / 5);
        System.out.println("moveToY :" + moveToY);

        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(centerX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(centerX * 1 / 5, moveToY))
                .release().perform();
    }

    }

