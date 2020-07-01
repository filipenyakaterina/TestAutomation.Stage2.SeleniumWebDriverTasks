package Hardcore.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Executor extends Helper {
    private static JavascriptExecutor javascriptExecutor;

    Executor(WebDriver driver) {
        super(driver);
        Executor.javascriptExecutor = (JavascriptExecutor) Executor.driver;
    }

    public static void clickElement(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }
}
