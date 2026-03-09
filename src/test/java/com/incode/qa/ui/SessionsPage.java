package com.incode.qa.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SessionsPage extends AbstractPageObject {
    private final String baseUrl;

    private final By rowLocator = By.xpath("//td[@data-clarity-label='clicked_item_sessions_sessionId']/ancestor::tr");
    private final By firstRowNameLocator = By.xpath("(//tr[@class='session-row'])[1]/td[contains(text(),'Hernandez')]");

    private final By ocrFullNameLocator = By.xpath("//h5[contains(text(),'Nombre completo (OCR)')]/following-sibling::span[contains(@class, 'content')]");

    public SessionsPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    public void clickFirstSessionId() {
        WebElement firstRow = waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(rowLocator));
        click(firstRow);
    }

    public String getFirstNameFromTable() {
        return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(firstRowNameLocator))
                .getText()
                .trim();
    }

    public String getOcrFullNameFromDetails() {
        return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(ocrFullNameLocator))
                .getText()
                .trim();
    }

    public boolean isTableNameEqualToOcrName() {
        String tableName = getFirstNameFromTable();
        String ocrName = getOcrFullNameFromDetails();
        return tableName.equalsIgnoreCase(ocrName);
    }
}
