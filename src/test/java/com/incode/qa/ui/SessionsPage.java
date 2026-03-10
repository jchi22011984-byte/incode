package com.incode.qa.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SessionsPage extends AbstractPageObject {
    private final String baseUrl;

    private final By firstRowNameLocator = By.xpath("//td[contains(.,'Hernandez')]");
    private final By ocrFullNameLocator = By.xpath("//h5[contains(text(),'Nombre completo (OCR)')]/following-sibling::span[contains(@class, 'content')]");

    private final By addFaceButtonLocator = By.xpath("//button[@data-clarity-label='clicked_button_singleSession_addFaceToDatabase']");
    private final By identitiesLink = By.xpath("//a[@data-clarity-label='clicked_sideMenu_identities']");


    public SessionsPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    private By buildRowLocator(String sessionId) {
        return By.xpath("//td[@data-clarity-label='clicked_item_sessions_sessionId' " +
                "and normalize-space(text())='" + sessionId + "']/ancestor::tr[@class='session-row']");
    }

    public void clickSessionById(String sessionId) {
        WebElement row = waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(buildRowLocator(sessionId)));
        click(row);
    }

    public String getFirstNameFromTable(String sessionId) {
        return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(firstRowNameLocator))
                .getText()
                .trim();
    }

    public String getOcrFullNameFromDetails() {
        return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(ocrFullNameLocator))
                .getText()
                .trim();
    }

    public boolean isTableNameEqualToOcrName(String sessionId) {
        String tableName = getFirstNameFromTable(sessionId);
        String ocrName = getOcrFullNameFromDetails();
        return tableName.equalsIgnoreCase(ocrName);
    }

    public void clickAddFaceButton() {
        clickWithRetry(addFaceButtonLocator, 10);
    }

    public void clickIdentitiesLink() {
        WebElement link = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(identitiesLink));
        click(link);
    }
}