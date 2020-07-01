package HurtMePlenty.helper;

import org.openqa.selenium.WebDriver;

public abstract class Helper {
    protected static WebDriver driver;

    protected Helper(WebDriver driver) {
        Helper.driver = driver;
    }
}
