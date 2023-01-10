package ru.netology.qa;


        import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.MobileElement;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.concurrent.TimeUnit;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import ru.netology.qa.Sourse.Sourses;


public class UIAutomatorAppiumTest {

    Sourses sourses = new Sourses();

    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "someDevice");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyStringTestTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.isDisplayed();
        el1.sendKeys(sourses.getTextString());
        String e1 = el1.getText();
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.isDisplayed();
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el3.isDisplayed();
        el3.clear();
        el3.sendKeys(sourses.getEmptyString());
        el2.click();
        MobileElement el4 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        el4.isDisplayed();
        String actual = el4.getText();
        String expected = sourses.getTextString();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void newActivityOpenTest() {
        MobileElement el1 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.isDisplayed();
        el1.sendKeys(sourses.getTextString());
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el2.isDisplayed();
        el2.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        el3.isDisplayed();
        String actual = el3.getText();
        String expected = sourses.getTextString();
        Assertions.assertEquals(expected, actual);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
