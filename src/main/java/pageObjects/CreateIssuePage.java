package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePage extends BasePageObject{

    @FindBy(id = "create-issue-submit")
    protected WebElement creatIssueSubmit;

    @FindBy(partialLinkText = "MTP-")
    protected WebElement MTPLink;

    @FindBy(id = "opsbar-operations_more")
    protected WebElement moreButton;

    @FindBy(css = "#delete-issue")
    protected WebElement deleteIssueButton;

    @FindBy(id = "delete-issue-submit")
    protected WebElement deleteIssueSubmitButton;

    @FindBy(partialLinkText = "project")
    protected WebElement projects;

    @FindBy(xpath = "//*[@id='create-subtask']/a/span")
    protected WebElement subtaskLink;

    @FindBy(xpath = "//*[@id='issues-subnavigation-title']")
    protected WebElement openIssuesTitle;

    @FindBy(id = "summary")
    protected WebElement summary;

    @FindBy(id = "summary-val")
    protected WebElement summaryValue;

    @FindBy(id = "create_link")
    protected WebElement createLink;

    @FindBy(xpath = "//*[@id='issuetype-field']")
    protected WebElement issueTypeField;

    @FindBy(xpath = "//*[@id='project-field']")
    private WebElement projectField;

    @FindBy(xpath = "//*[@id='create-issue-dialog']/div[2]/div[1]/div/form/div[2]/div/a")
    protected WebElement cancelButton;

    @FindBy(xpath = "//*[@id='create-issue-dialog']/div[2]/div[1]/div/form/div[1]/div[2]/div[1]/div")
    protected WebElement errorMessageToCreate;

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

    public void pressDeleteIssueSubmitButton() {
        press(deleteIssueSubmitButton);
    }

    public void pressDeleteIssueButtonIsAvailable() {
        if(deleteIssueButton.isEnabled() && deleteIssueButton.isDisplayed()) {
            press(deleteIssueButton);
        }
    }

    public String validateSummaryValueToCreateIssue() { return validateAssertEquals(summaryValue); }

    public void pressMTPProjectNameLink() { press(MTPLink); }

    public void pressCreateIssueSubmitButton() { press(creatIssueSubmit); }

    public void enterNewProject(String projectName) {
        enter(projects, projectName);
    }

    public void enterNewSummary(String newSummary) { enter(summary,newSummary); }

    public void enterNewIssueType(String newIssueType) { enter(issueTypeField, newIssueType); }

    public void pressCreateMenuButton() { press(createLink); }

    public void openIssuePage(String url) {
        goToUrl(url);
    }

    public void pressMoreMenuButton() {
        press(moreButton);
    }

    public void deleteIssue() {
        pressMoreMenuButton();
        pressDeleteIssueButtonIsAvailable();
        pressDeleteIssueSubmitButton();
    }

    public void verifyProjectField(String projectName) {
        String projectNameValue = projectField.getAttribute("value");
        Assert.assertEquals(projectName, projectNameValue);
    }

    public void verifyIssueType(String issueType) {
        String issueTypeValue = issueTypeField.getAttribute("value");
        Assert.assertEquals(issueType, issueTypeValue);
    }

    public boolean validateCreateSubtaskIsAvailable() { return validateIsEnabled(subtaskLink); }

    public String validateErrorMessageToCreateIssue() { return validateAssertEquals(errorMessageToCreate); }

    public void waitForOpenIssueTitle() { waitForVisibility(openIssuesTitle); }

    public void pressCancelButtonToCreateIssue() { press(cancelButton); }
}
