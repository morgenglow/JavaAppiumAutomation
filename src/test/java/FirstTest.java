import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void SetUp() throws Exception{
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10.0");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".onboarding.InitialOnboardingActivity");
        capabilities.setCapability("app","C:\\Users\\morge\\IdeaProjects\\JavaAppiumAutomation\\apks\\Wikipedia_v2.7.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void titleTest(){
        WebElement title = driver.findElementById("org.wikipedia:id/primaryTextView");
        Assert.assertEquals(title.getText(), "The Free Encyclopedia\n" +
                "…in over 300 languages");
        System.out.println("Title test is over!");
    }

    @Test
    public void skipTest(){
        WebElement continueButton = driver.findElementById("org.wikipedia:id/fragment_onboarding_forward_button");
        continueButton.click();
        continueButton.click();
        continueButton.click();
        WebElement getStartedButton = driver.findElementById("org.wikipedia:id/fragment_onboarding_done_button");
        System.out.println("Second test is over!");
    }
}
