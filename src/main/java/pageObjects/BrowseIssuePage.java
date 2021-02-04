package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseIssuePage extends BasePageObject {

    @FindBy(id = "stalker")
    protected WebElement header;

    public BrowseIssuePage(WebDriver driver) {
        super(driver);
    }

    public void openTestIssuePage(String issue) {
        goToUrl(issue);
    }

    public boolean validateHeaderIsAvailable() {
        return validateIsEnabled(header);
    }
}
