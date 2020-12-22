import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageObjects.BrowseProjectPage;
import pageObjects.CreateIssuePage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utils.Util;

public class TestScript {

    private final LoginPage login = new LoginPage(Util.DRIVER);
    private final BrowseProjectPage browseProject = new BrowseProjectPage(Util.DRIVER);
    private final MainPage mainPage = new MainPage(Util.DRIVER);
    private final CreateIssuePage createIssue = new CreateIssuePage(Util.DRIVER);

    @BeforeAll
    public static void setup() {
        System.setProperty(Util.WEBDRIVER, Util.CHROME_DRIVER_LOCATION);
    }

    @BeforeEach
    public void beforeEach() {
        login.login();
    }

    @ParameterizedTest()
    @DisplayName("Browse Projects")
    @CsvFileSource(resources = "/BrowseProjectData.csv", numLinesToSkip = 1)
    public void browseProjectIsPossible(String url, String assertData) {
        String fullURL = String.format("%s/projects/%s/summary", Util.BASE_URL, url);
        browseProject.openProjectPage(fullURL);
        Assert.assertEquals(browseProject.validateProjectKeyToBrowseProject(), assertData );
    }

    @Test
    public void createIssueHappyWay() {
        createIssue.pressCreateMenuButton();
        createIssue.enterNewSummary("Success!");
        createIssue.pressCreateIssueSubmitButton();
        createIssue.pressMTPProjectNameLink();
        Assert.assertEquals(createIssue.validateSummaryValueToCreateIssue(),"Success!");
        createIssue.deleteIssue();
    }

    @ParameterizedTest()
    @DisplayName("Create SubTask for Projects")
    @CsvFileSource(resources = "/CreateSubtaskData.csv", numLinesToSkip = 1)
    public void createSubTaskForProjectsIsPossible(String issue) {
        String fullURL = String.format("%s/browse/%s", Util.BASE_URL, issue);
        createIssue.openIssuePage(fullURL);
        createIssue.pressMoreMenuButton();
        Assert.assertTrue("create subtask should be available", createIssue.validateCreateSubtaskIsAvailable());
    }

    @AfterEach
    public void afterEach() {
        browseProject.setMainProject();
        mainPage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        Util.DRIVER.manage().deleteAllCookies();
        Util.DRIVER.close();
    }
}
