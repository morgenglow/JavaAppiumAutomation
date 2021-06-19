package libs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import libs.ui.MainPageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest{
    protected AppiumDriver driver;
    private String URL ="http://127.0.0.1:4723/wd/hub";
    public MainPageObject mainPageObject;

    @Before
    public void SetUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10.0");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\morge\\IdeaProjects\\JavaAppiumAutomation\\apks\\Wikipedia_v2.7.apk");
        driver = new AndroidDriver(new URL(URL), capabilities);
        mainPageObject = new MainPageObject(driver);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
