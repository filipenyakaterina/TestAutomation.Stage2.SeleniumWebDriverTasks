/**
 * Задание Hurt Me Plenty
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
 * 8. Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
 * 9. Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
 */

package HurtMePlenty.test;

import HurtMePlenty.dataEntity.InstancesData;
import HurtMePlenty.formatter.ValueFormatter;
import HurtMePlenty.page.GoogleCloudHomePage;
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
                        "n1-standard-8 (vCPUs: 8, RAM: 30GB)", "1", "NVIDIA Tesla V100", "2x375 GB",
                        "Frankfurt (europe-west3)", "1 Year")},
        };
    }

    @Test(description = "Check field 'VM class' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkVMclass(InstancesData testInstancesData) {
        String VMclass = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().
                fillComputeEngineForm(testInstancesData).clickAddToEstimate().getVMclass();
        Assert.assertTrue(testInstancesData.getMachineClass().toLowerCase().contains(ValueFormatter.getValueFromString(VMclass.toLowerCase())));
    }

    @Test(description = "Check field 'Instance Type' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkInstanceType(InstancesData testInstancesData) {
        String instanceType = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).clickAddToEstimate().getInstanceType();
        Assert.assertTrue(testInstancesData.getMachineType().toLowerCase().contains(ValueFormatter.getValueFromString(instanceType).toLowerCase()));
    }

    @Test(description = "Check field 'Region' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkRegion(InstancesData testInstancesData) {
        String region = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).clickAddToEstimate().getRegion();
        Assert.assertTrue(testInstancesData.getDatacenterLocation().toLowerCase().contains(ValueFormatter.getValueFromString(region).toLowerCase()));
    }

    @Test(description = "Check field 'Local SSD' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkLocalSSD(InstancesData testInstancesData) {
        String localSSD = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getLocalSSD();
        Assert.assertTrue(localSSD.toLowerCase().contains(testInstancesData.getLocalSSD().toLowerCase()));
    }

    @Test(description = "Check field 'Commitment Term' after calculation price at the Google Cloud Platform Pricing Calculator",
            dataProvider = "testData")
    public void checkCommitmentTerm(InstancesData testInstancesData) {
        String commitmentTerm = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getCommitmentTerm();
        Assert.assertTrue(testInstancesData.getCommittedUsage().toLowerCase().contains(ValueFormatter.getValueFromString(commitmentTerm).toLowerCase()));
    }

    @Test(description = "Check that the value of total estimate cost after the calculation of price with automation test matches with the value after manual test.",
            dataProvider = "testData")
    public void checkTotalEstimateCost(InstancesData testInstancesData) {
        final String TOTAL_ESTIMATE_COST_BY_MANUAL_TEST = "USD 1,082.77 per 1 month";

        String totalEstimateCost = new GoogleCloudHomePage(driver).openPage().
                search(SEARCH_QUERY).followLinkWithSearchResult().
                selectComputeEngine().fillComputeEngineForm(testInstancesData).
                clickAddToEstimate().getTotalEstimateCost();
        Assert.assertTrue(totalEstimateCost.toLowerCase().contains(TOTAL_ESTIMATE_COST_BY_MANUAL_TEST.toLowerCase()));
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
