package Hardcore.pageElement;

import Hardcore.helper.Executor;
import Hardcore.helper.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MdSelect {
    private WebElement select;
    private WebElement option;
    private WebElement optionDiv;

    public MdSelect(WebElement select) {
        this.select = select;
    }

    private void expandSelect() {
        Executor.clickElement(select);
        Waiter.waitUntilAttributeAriaExpandedToBe(select, true);
    }

    private void findSelectOption(String optionText) {
        String elementXpath = "//div[@class = 'md-select-menu-container md-active md-clickable']//div[contains(text(),'" + optionText + "')]";

        optionDiv = select.findElement(By.xpath(elementXpath));
        option = select.findElement(By.xpath(elementXpath + "/ancestor::md-option"));
    }

    private void selectOption() {
        Executor.clickElement(option);
        Executor.clickElement(optionDiv);
        Waiter.waitUntilElementToBeVisible(select);
    }

    public void selectByValue(String optionText) {
        expandSelect();
        findSelectOption(optionText);
        selectOption();
    }
}
