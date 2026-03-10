package com.incode.qa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IdentitiesPage extends AbstractPageObject {

    private final String baseUrl;

    public IdentitiesPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    public void clickIdentityById(String identityId) {
        String selector = String.format("//button[contains(text(),'%s')]", identityId);
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(By.xpath(selector)))
                .click();
    }
}