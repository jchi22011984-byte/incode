package com.incode.qa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends AbstractPageObject {

    private final String baseUrl;
    private final By USER_NAME = By.id("email");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.cssSelector("button.button.green.full[data-clarity-label='clicked_button_login']");
    private final By LOADING_INDICATOR = By.cssSelector("svg[viewBox='0 0 4 18']");
    private final By SESSIONS_LINK = By.cssSelector("a[data-clarity-label='clicked_sideMenu_sessions']");
    private final By MENU_BUTTON = By.cssSelector("button.menuNav.show[aria-controls='primary-navigation']");
    private final By FLOWS = By.xpath("//a[@href='/flows' and @data-clarity-label='clicked_sideMenu_flows']");

    public DashboardPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    public void goToBaseAndPath() {
        String fullUrl = baseUrl + getPath();
        getDriver().navigate().to(fullUrl);
        ensureIsCurrent();
    }

    public void loginWithRetry(String email, String password) {
        retryUntil(() -> {
            setText(find(USER_NAME), email);
            find(USER_NAME).sendKeys(Keys.TAB);
            setText(find(PASSWORD), password);
            waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
            return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(LOADING_INDICATOR)) != null;
        }, 10);
    }

    public void openMenu() {
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(MENU_BUTTON)).click();
    }

    public void goToSessionsPage() {
        openMenu();
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(SESSIONS_LINK)).click();
    }

    public void goToFlowsPage() {
        openMenu();
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(FLOWS)).click();
    }
}
