import org.junit.Assert;
import org.junit.jupiter.api.*;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import utils.Util;

public class TestScript {

    private final LoginPage login = new LoginPage(Util.DRIVER);
    private final MainPage mainPage = new MainPage(Util.DRIVER);

    public TestScript() {
    }

    @BeforeAll
    public static void setup() {
        System.setProperty(Util.WEBDRIVER, Util.CHROME_DRIVER_LOCATION);
    }


    @Test
    public void successfulLogin() {
        login.login();
        System.out.println(1);
        mainPage.pressProfileImage();
        System.out.println(2);
        Assert.assertTrue("logout button should be available", mainPage.validateLogoutButtonIsAvailable());
        System.out.println(3);
        mainPage.pressLogoutButton();
    }

    @Test
    public void loginWithoutUserInfos() {
        login.openBaseUrl();
        login.pressLoginButton();
        Assert.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void loginWithoutPassword() {
        login.openBaseUrl();
        login.enterValidUsername();
        login.pressLoginButton();
        Assert.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void loginWithInvalidPassword() {
        login.openBaseUrl();
        login.enterValidUsername();
        login.enterInvalidPassword();
        login.pressLoginButton();
        Assert.assertEquals(login.validateErrorMessage(),"Sorry, your username and password are incorrect - please try again.");
    }

    @Test
    public void successfulLogout() {
        login.login();
        mainPage.logout();
        Assert.assertTrue("a login button should be available", mainPage.validateLogoutTitle());
    }

    @AfterAll
    public static void cleanUp() {
        Util.DRIVER.manage().deleteAllCookies();
        Util.DRIVER.close();
    }

}
