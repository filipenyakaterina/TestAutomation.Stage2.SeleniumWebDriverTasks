package ICanWin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";

    private WebDriver driver;

    @FindBy(id = "paste_code")
    private WebElement codeInput;

    @FindBy(name = "paste_expire_date")
    private WebElement pasteExpirationSelect;

    @FindBy(name = "paste_name")
    private WebElement pasteNameInput;

    @FindBy(id = "submit")
    private WebElement createButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public void createPaste(String codeText, String pasteExpirationText, String pasteName) {
        codeInput.sendKeys(codeText);
        new Select(pasteExpirationSelect).selectByVisibleText(pasteExpirationText);
        pasteNameInput.sendKeys(pasteName);
        createButton.click();
    }
}
