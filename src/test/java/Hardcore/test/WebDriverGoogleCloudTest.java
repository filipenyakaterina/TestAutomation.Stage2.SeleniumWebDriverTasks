/**
 * Задание Hardcore
 * При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object.
 * Автоматизировать следующий сценарий:
 * <p>
 * 1. Открыть https://cloud.google.com/
 * 2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
 * 3. Запустить поиск, нажав кнопку поиска.
 * 4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
 * 5. Активировать раздел COMPUTE ENGINE вверху страницы
 * 6. Заполнить форму следующими данными:
 * Number of instances: 4
 * What are these instances for?: оставить пустым
 * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
 * VM Class: Regular
 * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
 * Выбрать Add GPUs
 * Number of GPUs: 1
 * GPU type: NVIDIA Tesla V100
 * Local SSD: 2x375 Gb
 * Datacenter location: Frankfurt (europe-west3)
 * Commited usage: 1 Year
 * 7. Нажать Add to Estimate
 * 8. Выбрать пункт EMAIL ESTIMATE
 * 9. В новой вкладке открыть https://10minutemail.com или аналогичный сервис для генерации временных email'ов
 * 10. Скопировать почтовый адрес сгенерированный в 10minutemail
 * 11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
 * 12. Нажать SEND EMAIL
 * 13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем,
 * что отображается в калькуляторе
 */
package Hardcore.test;

import Hardcore.dataEntity.InstancesData;
import Hardcore.formatter.CostFormatter;
import Hardcore.page.EmailEstimatePage;
import Hardcore.page.GoogleCloudHomePage;
import Hardcore.page.MessagesListPage;
import Hardcore.page.TempMailHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WebDriverGoogleCloudTest {
    private final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{new InstancesData("4", "",
                        "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS", "Regular",
                        "n1-standard-8 (vCPUs: 8, RAM: 30GB)", "1", "NVIDIA Tesla V100",
                        "2x375 GB", "Frankfurt (europe-west3)", "1 Year")},
        };
    }

    @Test(description = "Test verifies that the price calculated by the Google Cloud Platform Pricing Calculator matches the price that was sent by email",
            dataProvider = "testData")
    public void checkEmailEstimate(InstancesData testInstancesData) {
        EmailEstimatePage emailEstimatePage = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().clickEmailEstimate();

        String emailAddress = new TempMailHomePage(driver).openPage().getEmailAddress();
        String costFromCalculator = emailEstimatePage.openPage().sendEmail(emailAddress).getEstimatedCost();
        String costFromEmail = new MessagesListPage(driver).openPage().getEstimateCost();

        Assert.assertEquals(CostFormatter.getCostFromString(costFromCalculator),
                CostFormatter.getCostFromString(costFromEmail));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
