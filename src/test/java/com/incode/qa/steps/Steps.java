package com.incode.qa.steps;

import com.incode.qa.config.ApplicationProperties;
import com.incode.qa.ui.DashboardPage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class Steps {

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Given("I log in with configured credentials")
    public void login_with_configured_credentials() {
        dashboardPage.goToBaseAndPath();
        dashboardPage.loginWithRetry(
                applicationProperties.getEmail(),
                applicationProperties.getPassword()
        );
    }
}