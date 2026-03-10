package com.incode.qa.ui;

import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

@Data
public abstract class AbstractPageObject {
    @Getter
    private final WebDriver driver;
    private final int waitTimeOutSeconds;
    @Getter
    private String path;

    public AbstractPageObject(String path, WebDriver driver, int waitTimeOutSeconds) {
        this.path = path;
        this.driver = driver;
        this.waitTimeOutSeconds = waitTimeOutSeconds;
        this.driver.manage().window().maximize();
    }

    public void goToAndWait(String baseUrl) {
        getDriver().navigate().to(baseUrl + path);
        ensureIsCurrent();
    }

    public void ensureIsCurrent() {
        waitUntilTrueOrTimeout(driver -> driver.getCurrentUrl().contains(path));
    }

    public boolean isTextPresent(String text) {
        waitUntilTrueOrTimeout(driver -> driver.getPageSource().contains(text));
        return true;
    }

    protected <V> V waitUntilTrueOrTimeout(ExpectedCondition<V> isTrue) {
        Wait<WebDriver> wait = new WebDriverWait(this.driver, Duration.ofSeconds(waitTimeOutSeconds))
                .ignoring(StaleElementReferenceException.class);
        try {
            return wait.until(isTrue);
        } catch (TimeoutException rte) {
            throw new TimeoutException(rte.getMessage());
        }
    }

    public void setText(WebElement element, String text) {
        scrollIntoView(element);
        waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        try {
            element.sendKeys(text);
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value=arguments[1];", element, text);
        }
    }

    public void submit(WebElement element) {
        element.submit();
    }

    public void selectDropdownByText(WebElement element, String visibleText) {
        Select filterSelect = new Select(element);
        waitForDropdownItems(element);
        filterSelect.selectByVisibleText(visibleText);
    }

    private void waitForDropdownItems(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(waitTimeOutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement find(By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException(ex.getMessage());
        }
    }

    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        System.out.println("[INFO] Scroll executed for element: " + element);
    }

    /**
     * Retry helper para acciones que pueden fallar transitoriamente
     */
    protected void retryUntil(java.util.function.Supplier<Boolean> action, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                if (action.get()) return;
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw new RuntimeException("Max retries reached", e);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
    }

    public void click(WebElement element) {
        scrollIntoView(element);
        try {
            waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        }
    }

    public void clickWithRetry(By locator, int maxAttempts) {
        retryUntil(() -> {
            try {
                WebElement element = waitUntilTrueOrTimeout(ExpectedConditions.elementToBeClickable(locator));
                scrollIntoView(element);
                element.click();
                return true; // éxito
            } catch (WebDriverException e) {
                try {
                    WebElement element = find(locator);
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
                    return true; // éxito con JS
                } catch (Exception inner) {
                    return false; // reintentar
                }
            }
        }, maxAttempts);
    }

}