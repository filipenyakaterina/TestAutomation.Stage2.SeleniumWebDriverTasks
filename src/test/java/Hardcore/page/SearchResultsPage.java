package Hardcore.page;

import Hardcore.helper.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends AbstractPage {
    private final String SEARCH_QUERY;

    protected SearchResultsPage(WebDriver driver, String searchQuery) {
        super(driver);
        if (!driver.getCurrentUrl().contains(GoogleCloudHomePage.HOMEPAGE_URL)) {
            throw new IllegalStateException("This is not the Google Cloud page!");
        }
        this.SEARCH_QUERY = searchQuery;
    }

    public AbstractPage openPage() {
        throw new RuntimeException("Cannot open search page without searching.");
    }

    public GoogleCloudPlatformPricingCalculatorPage followLinkWithSearchResult() {
        Waiter.waitUntilElementAppears(By.linkText(SEARCH_QUERY));
        driver.findElement(By.linkText(SEARCH_QUERY)).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
