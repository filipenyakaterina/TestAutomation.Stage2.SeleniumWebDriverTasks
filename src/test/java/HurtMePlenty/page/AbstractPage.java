package HurtMePlenty.page;

import HurtMePlenty.helper.HelperInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        HelperInitializer.initHelpers(driver);
    }

    protected abstract AbstractPage openPage();
}
