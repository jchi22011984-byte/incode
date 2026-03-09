package com.incode.qa.steps;

import com.incode.qa.config.ApplicationProperties;
import com.incode.qa.ui.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class Steps {

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Given("I go to the base login page")
    public void go_to_base_login_page() {
        dashboardPage.goToBaseAndPath();
    }

    @When("I log in with configured credentials")
    public void login_with_configured_credentials() {
        dashboardPage.loginWithRetry(
                applicationProperties.getEmail(),
                applicationProperties.getPassword()
        );
    }

}