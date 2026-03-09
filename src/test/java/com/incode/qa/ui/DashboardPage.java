package com.incode.qa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends AbstractPageObject {

    private final String baseUrl;
    private final By USER_NAME = By.id("email");
    private final By PASSWORD = By.id("password");
    private final By LOGIN_BUTTON = By.cssSelector("button.button.green.full[data-clarity-label='clicked_button_login']");
    private final By LOADING_INDICATOR = By.cssSelector("svg[viewBox='0 0 4 18']");

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
            WebElement btn = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
            btn.click();
            return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(LOADING_INDICATOR)) != null;
        }, 10);
    }

}