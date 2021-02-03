import org.junit.jupiter.api.*;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utils.Util;

public class TestScriptLogin {

    private final LoginPage login = new LoginPage(Util.DRIVER);
    private final MainPage mainPage = new MainPage(Util.DRIVER);

    public TestScriptLogin() {
    }

    @BeforeAll
    public static void setup() {
        System.setProperty(Util.WEBDRIVER, Util.CHROME_DRIVER_LOCATION);
    }

    @Test
    public void successfulLogin() {
        login.login();
        mainPage.pressProfileImage();
        Assertions.assertTrue(mainPage.validateLogoutButtonIsAvailable(), "logout button should be available");
        mainPage.pressLogoutButton();
    }

    @Test
    public void loginWithoutUserInfos() {
        login.openBaseUrl();
        login.pressLoginButton();
        Assertions.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void loginWithoutPassword() {
        login.openBaseUrl();
        login.enterValidUsername();
        login.pressLoginButton();
        Assertions.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void loginWithInvalidPassword() {
        login.openBaseUrl();
        login.enterValidUsername();
        login.enterInvalidPassword();
        login.pressLoginButton();
        Assertions.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void successfulLogout() {
        login.login();
        mainPage.logout();
        Assertions.assertTrue(mainPage.validateLogoutTitle(), "a login button should be available");
    }

    @AfterAll
    public static void cleanUp() {
        Util.DRIVER.manage().deleteAllCookies();
        Util.DRIVER.close();
    }
}
