package libs.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(By by, String errormessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errormessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElement(By by, String errormessage) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.withMessage(errormessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, String errormessage) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errormessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElement(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public void skipScreen (){
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find onboarding skip button",
                5
        );
    }

    public void clickOnBookmarks(){
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find saved bookmarks",
                5
        );
    }


    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action= new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width/2);
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x,end_y)
                .release()
                .perform();
    }
    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped >max_swipes){
                waitForElementNotPresent(by, "Cannot find element by swipe up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElement(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x-1, middle_y)
                .waitAction(300)
                .moveTo(left_x-1, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent (By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute (By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElement(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by) {
        try {
            WebElement element = driver.findElement(by);
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            throw new AssertionError("Element " + by.toString() + " Not found on the page");
        }
    }

    public void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    public void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }
    public void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }

}
