package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By pageContainer = byTestId("home-page");
    private final By logoutButton  = byTestId("header-logout-button");
    private final By cartBadge     = byTestId("header-cart-badge");

    public boolean isLoaded() {
        waitVisible(pageContainer);
        return true;
    }

    public boolean isLogoutVisible() {
        return isDisplayed(logoutButton);
    }

    public LoginPage logout() {
        click(logoutButton);
        return new LoginPage();
    }
}
