package Hardcore.page;

import Hardcore.helper.Executor;
import Hardcore.helper.Switcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailEstimatePage extends AbstractPage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement email;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public EmailEstimatePage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudPlatformPricingCalculatorPage.CALCULATOR_URL)) {
            throw new IllegalStateException("This is not the Google Cloud Platform Pricing Calculator page!");
        }
    }

    @Override
    public EmailEstimatePage openPage() {
        Switcher.switchToOtherTab();
        Switcher.switchToFrame();
        return this;
    }

    public PriceCalculationResult sendEmail(String emailValue) {
        email.sendKeys(emailValue);
        Executor.clickElement(sendEmailButton);
        return new PriceCalculationResult(driver);
    }
}
