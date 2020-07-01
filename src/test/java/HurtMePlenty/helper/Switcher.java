package HurtMePlenty.helper;

import org.openqa.selenium.WebDriver;

public class Switcher extends Helper {
    private static final String FRAME_NAME = "myFrame";
    private static final int FRAME_NUMBER = 0;

    Switcher(WebDriver driver) {
        super(driver);
    }

    public static void switchToFrame() {
        driver.switchTo().defaultContent();
        Waiter.waitForFrame(FRAME_NUMBER);
        Waiter.waitForFrame(FRAME_NAME);
    }
}
