package HurtMePlenty.page;

import HurtMePlenty.dataEntity.InstancesData;
import HurtMePlenty.helper.Executor;
import HurtMePlenty.helper.Waiter;
import HurtMePlenty.pageElement.MdSelect;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComputeEnginePage extends AbstractPage {
    @FindBy(xpath = "//input[@name = 'quantity']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//label[text()='What are these instances for?']//following::input[1]")
    private WebElement purposeOfUse;

    @FindBy(xpath = "//label[text()='Operating System / Software']//following::md-select[1]")
    private WebElement operatingSystem;

    @FindBy(xpath = "//label[text()='Machine Class']//following::md-select[1]")
    private WebElement machineClass;

    @FindBy(xpath = "//label[text()='Machine type']//following::md-select[1]")
    private WebElement machineType;

    @FindBy(xpath = "//md-input-container/md-checkbox")
    private WebElement addGPUs;

    @FindBy(xpath = "//label[text()='Number of GPUs']//following::md-select[1]")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//label[text()='GPU type']//following::md-select[1]")
    private WebElement typeOfGPUs;

    @FindBy(xpath = "//label[text()='Local SSD']//following::md-select[1]")
    private WebElement localSSD;

    @FindBy(xpath = "//label[text()='Datacenter location']//following::md-select[1]")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//label[text()='Committed usage']//following::md-select[1]")
    private WebElement committedUsage;

    public ComputeEnginePage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudPlatformPricingCalculatorPage.CALCULATOR_URL)) {
            throw new IllegalStateException("This is not the Google Cloud Platform Pricing Calculator page!");
        }
    }

    @Override
    public AbstractPage openPage() {
        throw new RuntimeException("Cannot open compute engine page without selection Compute Engine tab.");
    }

    public GoogleCloudPlatformPricingCalculatorPage fillComputeEngineForm(InstancesData instancesData) {
        numberOfInstances.sendKeys(instancesData.getNumberOfInstances());
        purposeOfUse.sendKeys(instancesData.getPurposeOfUse());

        new MdSelect(operatingSystem).selectByValue(instancesData.getOperatingSystem());
        new MdSelect(machineClass).selectByValue(instancesData.getMachineClass());
        new MdSelect(machineType).selectByValue(instancesData.getMachineType());

        Executor.clickElement(addGPUs);
        Waiter.waitUntilElementToBeVisible(numberOfGPUs);
        new MdSelect(numberOfGPUs).selectByValue(instancesData.getNumberOfGPUs());
        new MdSelect(typeOfGPUs).selectByValue(instancesData.getTypeOfGPUs());

        new MdSelect(localSSD).selectByValue(instancesData.getLocalSSD());
        new MdSelect(datacenterLocation).selectByValue(instancesData.getDatacenterLocation());
        new MdSelect(committedUsage).selectByValue(instancesData.getCommittedUsage());

        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
