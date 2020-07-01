/**
 * Задание Bring It On
 * При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object.
 * Автоматизировать следующий сценарий:
 * 1. Открыть https://pastebin.com  или аналогичный сервис в любом браузере
 * 2. Создать New Paste со следующими деталями:
 * Код:
 * git config --global user.name  "New Sheriff in Town"
 * git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
 * git push origin master --force
 * <p>
 * Syntax Highlighting: "Bash"
 * Paste Expiration: "10 Minutes"
 * Paste Name / Title: "how to gain dominance among developers"
 * 3. Сохранить paste и проверить следующее:
 * Заголовок страницы браузера соответствует Paste Name / Title
 * Синтаксис подвечен для bash
 * Проверить что код соответствует введенному в пункте 2
 */

package BringItOn.test;

import BringItOn.dataEntity.Paste;
import BringItOn.page.PasteCreatingResultPage.FontCssStyle;
import BringItOn.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class WebDriverPastebinTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{new Paste("git config --global user.name  \"New Sheriff in Town\"\n" +
                        "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                        "git push origin master --force", "Bash", "10 Minutes",
                        "how to gain dominance among developers")},
        };
    }

    @Test(description = "Check that the title of the browser page matches Paste Name / Title",
            dataProvider = "testData")
    public void checkTitle(Paste paste) {
        String title = new PastebinHomePage(driver).openPage().
                createPaste(paste).getTitle();
        Assert.assertEquals(title, paste.getTitle(), "The title of the browser page doesn't match Paste Name.");
    }

    @Test(description = "Check syntax highlighting for Bash code : bash commands have font-color=rgba(194, 12, 185, 1) and font-weight=700",
            dataProvider = "testData")
    public void checkSyntaxHighlighting(Paste paste) {
        final FontCssStyle SYNTAX_HIGHLIGHTING = new FontCssStyle("rgba(194, 12, 185, 1)", "700");

        List<FontCssStyle> fontStyles = new PastebinHomePage(driver).openPage().
                createPaste(paste).getBashCommandsCssStyles();
        Assert.assertTrue(fontStyles.stream().allMatch(style -> style.equals(SYNTAX_HIGHLIGHTING)),
                "Not all bash commands have appropriate highlighting (font-color=rgba(194, 12, 185, 1) and font-weight=700).");
    }

    @Test(description = "Check that the text of Code field matches the code of the creating paste",
            dataProvider = "testData")
    public void checkCodeText(Paste paste) {
        String code = new PastebinHomePage(driver).openPage().
                createPaste(paste).getCodeText();
        Assert.assertEquals(code, paste.getCode(),
                "The text of Code field at the New Paste Page doesn't match the code of the creating paste.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
