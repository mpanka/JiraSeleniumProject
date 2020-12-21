import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageObjects.BrowseProjectPage;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utils.Util;

public class TestScript {

    private final LoginPage login = new LoginPage(Util.DRIVER);
    private final BrowseProjectPage browseProject = new BrowseProjectPage(Util.DRIVER);
    private final MainPage mainPage = new MainPage(Util.DRIVER);

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
        browseProject.openProjectPage(url);
        Assert.assertEquals(browseProject.validateProjectKeyToBrowseProject(), assertData );
    }

    @Test
    public void selectProject() {
        browseProject.pressProjectMenuButton();
        browseProject.pressProjectViewAllButton();
        browseProject.filterToMTPProject();
        browseProject.pressProjectNameLink();
        Assert.assertEquals(browseProject.validateProjectTitleToBrowseProject(), "MTP" );
    }

    @AfterEach
    public void afterEach() {
        mainPage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        Util.DRIVER.manage().deleteAllCookies();
        Util.DRIVER.close();
    }
}
