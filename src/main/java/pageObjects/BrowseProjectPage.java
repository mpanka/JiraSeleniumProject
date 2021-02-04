package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowseProjectPage extends BasePageObject {

    @FindBy(css = ".project-meta-value:nth-child(4)")
    protected WebElement projectKey;

    @FindBy(linkText = "Main Testing Project")
    protected WebElement nameLink;

    @FindBy(id = "project-filter-text")
    protected WebElement projectFilter;

    @FindBy(id = "browse_link")
    protected WebElement projectButton;

    @FindBy(id = "project_view_all_link_lnk")
    protected WebElement projectViewAllButton;

    public BrowseProjectPage(WebDriver driver) {
        super(driver);
    }

    public void openProjectPage(String url) {
        goToUrl(url);
    }

    public String validateProjectKeyToBrowseProject() {
        return validateAssertEquals(projectKey);
    }

    public void pressProjectNameLink() {
        press(nameLink);
    }

    public void filterToMTPProject() {
        enter(projectFilter, "MTP");
    }

    public void pressProjectMenuButton() {
        press(projectButton);
    }

    public void pressProjectViewAllButton() {
        press(projectViewAllButton);
    }

    public void setMainProject() {
        pressProjectMenuButton();
        pressProjectViewAllButton();
        filterToMTPProject();
        pressProjectNameLink();
    }
}
