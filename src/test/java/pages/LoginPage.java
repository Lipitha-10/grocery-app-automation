package pages;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    // Locators: all from data-testid
    private final By pageContainer = byTestId("login-page");
    private final By emailInput    = byTestId("login-email-input");
    private final By passwordInput = byTestId("login-password-input");
    private final By submitButton  = byTestId("login-submit-button");
    private final By errorMessage  = byTestId("login-error-message");

    public boolean isLoaded() {
        try {
            waitVisible(pageContainer);   // waits up to timeoutSeconds
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    // Small actions, so tests can compose them
    public LoginPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public void clickLogin() {
        click(submitButton);
    }

    // Successful login moves to the Home page, so this returns HomePage
    public HomePage loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return new HomePage();
    }

    // Failed login stays on the Login page, so this returns LoginPage
    public LoginPage loginExpectingError(String email, String password) {
        if (!email.isEmpty()) enterEmail(email);
        if (!password.isEmpty()) enterPassword(password);
        clickLogin();
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
