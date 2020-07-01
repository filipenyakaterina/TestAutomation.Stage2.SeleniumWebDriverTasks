package Hardcore.page;

import Hardcore.helper.HelperInitializer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        HelperInitializer.initHelpers(driver);
    }

    public abstract AbstractPage openPage();
}
