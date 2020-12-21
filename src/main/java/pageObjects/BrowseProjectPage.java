package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseProjectPage extends BasePageObject{

    @FindBy(css = ".project-meta-value:nth-child(4)")
    protected WebElement projectKey;

    public BrowseProjectPage(WebDriver driver) {
        super(driver);
    }

    public void openProjectPage(String url) {
        goToUrl(url);
    }

    public String validateProjectKeyToBrowseProject() {
        return validateAssertEquals(projectKey);
    }
}
