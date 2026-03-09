package com.incode.qa.ui;

import org.openqa.selenium.*;

public class SessionsPage extends AbstractPageObject {
    private final String baseUrl;
    private final By rowLocator = By.xpath("//td[@data-clarity-label='clicked_item_sessions_sessionId']/ancestor::tr");

    public SessionsPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    public void clickFirstSessionId() {
        WebElement firstRow = find(rowLocator);
        click(firstRow);
    }
}