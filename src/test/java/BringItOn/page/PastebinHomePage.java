package BringItOn.page;

import BringItOn.dataEntity.Paste;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PastebinHomePage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://pastebin.com/";

    @FindBy(id = "paste_code")
    private WebElement codeInput;

    @FindBy(name = "paste_expire_date")
    private WebElement pasteExpirationSelect;

    @FindBy(name = "paste_format")
    private WebElement pasteSyntaxHighlightingSelect;

    @FindBy(name = "paste_name")
    private WebElement pasteNameInput;

    @FindBy(id = "submit")
    private WebElement createButton;

    public PastebinHomePage(WebDriver driver) {
        super(driver);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PasteCreatingResultPage createPaste(Paste paste) {
        codeInput.sendKeys(paste.getCode());
        new Select(pasteExpirationSelect).
                selectByVisibleText(paste.getPasteExpiration());
        new Select(pasteSyntaxHighlightingSelect).
                selectByVisibleText(paste.getSyntaxHighlighting());
        pasteNameInput.sendKeys(paste.getTitle());
        createButton.click();
        return new PasteCreatingResultPage(driver);
    }
}
