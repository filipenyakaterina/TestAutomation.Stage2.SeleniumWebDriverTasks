/**
 * Задание ICanWin
 * При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object.
 * Автоматизировать следующий сценарий:
 * 1. Открыть https://pastebin.com или аналогичный сервис в любом браузере
 * 2. Создать New Paste со следующими деталями:
 * Код: "Hello from WebDriver"
 * Paste Expiration: "10 Minutes"
 * Paste Name / Title: "helloweb"
 */

package ICanWin.test;

import ICanWin.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
                new Object[]{"Hello from WebDriver", "10 Minutes", "helloweb"},
        };
    }

    @Test(description = "Just a script to create a new paste", dataProvider = "testData")
    public void createNewPaste(String codeText, String pasteExpirationText, String pasteName) {
        new PastebinHomePage(driver).openPage().
                createPaste(codeText, pasteExpirationText, pasteName);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
