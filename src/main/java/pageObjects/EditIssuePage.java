package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditIssuePage extends BasePageObject {

    @FindBy(id = "edit-issue")
    protected WebElement editIssueButton;

    @FindBy(xpath = "//*[@id='edit-issue-submit']")
    protected WebElement updateButton;

    @FindBy(xpath = "//*[@id='edit-issue-dialog']/div[2]/div[1]/div/form/div[2]/div/a")
    protected WebElement cancelButtonToEdit;

    @FindBy(xpath = "//*[@id='edit-issue-dialog']/div[2]/div[1]/div/form/div[1]/div/div[1]/div")
    protected WebElement errorMessageToEdit;

    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div")
    protected WebElement updateAlert;

    public EditIssuePage(WebDriver driver) {
        super(driver);
    }

    public void waitForCreateLink() {
        waitForVisibility(createLink);
    }

    public void waitForUpdateAlert() {
        waitForVisibility(updateAlert);
    }

    public void openIssuePage(String issue) {
        goToUrl(issue);
    }

    public boolean validateEditIssueButtonIsAvailable() {
        return validateIsEnabled(editIssueButton);
    }

    public void pressUpdateButton() {
        press(updateButton);
    }

    public String validateSummaryValueToEditIssue() {
        return validateAssertEquals(summaryValue);
    }

    public String validateErrorMessageToEditIssue() {
        return validateAssertEquals(errorMessageToEdit);
    }

    public void pressCancelButtonToEdit() {
        press(cancelButtonToEdit);
    }

    public void enterNewSummary(String newSummary) {
        enter(summary, newSummary);
    }

    public void pressEditIssueButton() {
        press(editIssueButton);
    }
}
