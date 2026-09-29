package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(description = "Valid credentials land on Home")
    public void validLoginShouldOpenHomePage() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoaded(), "Login page did not load");
        HomePage homePage = loginPage.loginAs(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword"));

        Assert.assertTrue(homePage.isLoaded(), "Home page did not load after login");
        Assert.assertTrue(homePage.isLogoutVisible(), "Logout button should be visible after login");
    }

    @Test(description = "Empty fields show a required-fields error")
    public void emptyFieldsShouldShowError() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginExpectingError("", "");

        Assert.assertEquals(loginPage.getErrorMessage(), "Email and password are required");
    }

    @Test(description = "Wrong password shows an invalid-credentials error")
    public void wrongPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginExpectingError(ConfigReader.get("validEmail"), "WrongPass@1");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid email or password");
    }
}
