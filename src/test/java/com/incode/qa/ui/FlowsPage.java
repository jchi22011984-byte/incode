package com.incode.qa.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlowsPage extends AbstractPageObject {

    private final String baseUrl;
    private final By NEW_FLOW_BUTTON = By.xpath("//button[@data-clarity-label='clicked_button_flows_new']");
    private final By SAVE_CHANGES_BUTTON = By.xpath("//button[@data-clarity-label='clicked_button_flow_saveChanges'][1]");
    private final By CLOSE_NAV = By.xpath("//button[@class='close-nav' and @aria-label='Close navigation']");

    public FlowsPage(String baseUrl, String path, WebDriver driver, int waitTimeOutSeconds) {
        super(path, driver, waitTimeOutSeconds);
        this.baseUrl = baseUrl;
    }

    public void createNewActiveFlow() {
        clickWithRetry(NEW_FLOW_BUTTON, 5);
    }

    public void closeNav() {
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(CLOSE_NAV)).click();
    }

    public void addModuleToFlow(String moduleName) {
        By moduleLocator = By.xpath("//div[p[normalize-space(text())='" + moduleName + "']]//input[@type='checkbox']");
        WebElement element = waitUntilTrueOrTimeout(ExpectedConditions.presenceOfElementLocated(moduleLocator));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public void saveChanges() {
        clickWithRetry(SAVE_CHANGES_BUTTON, 5);
    }

    public boolean flowContainsModules(String module1, String module2, String module3) {
        return isModuleEnabled(module1) && isModuleEnabled(module2) && isModuleEnabled(module3);
    }

    private boolean isModuleEnabled(String moduleName) {
        By moduleLocator = By.xpath("//p[normalize-space(text())='" + moduleName + "']/preceding::input[@type='checkbox'][1]");
        return find(moduleLocator).isSelected();
    }

    public boolean isFlowPresent(String flowName) {
        goToAndWait(baseUrl);
        By flowLocator = By.xpath("//table[contains(@class,'flows-table')]//td[normalize-space(text())='" + flowName + "']");
        try {
            return waitUntilTrueOrTimeout(ExpectedConditions.visibilityOfElementLocated(flowLocator)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteFlow(String flowName) {
        By ellipsisButton = By.xpath("//tr[td[normalize-space(.)='" + flowName + "']]//button[@data-clarity-label='clicked_action_flows_moreActions']");
        WebElement ellipsis = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(ellipsisButton));
        ellipsis.click();

        By deleteButton = By.xpath("(//*[text()='Eliminar Flujo'])[1]");
        WebElement delete = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(deleteButton));
        delete.click();

        By confirmButton = By.xpath("(//button[text()='Confirm'])[1]");
        WebElement confirm = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(confirmButton));
        confirm.click();
    }
}