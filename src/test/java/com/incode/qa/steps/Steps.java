package com.incode.qa.steps;

import com.incode.qa.config.ApplicationProperties;
import com.incode.qa.ui.DashboardPage;
import com.incode.qa.ui.SessionsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class Steps {

    @Autowired
    private DashboardPage dashboardPage;
    @Autowired
    private SessionsPage sessionsPage;

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

    @When("I navigate to the sessions page")
    public void i_navigate_to_the_sessions_page() {
       dashboardPage.goToSessionsPage();
    }
    @When("I open a single session")
    public void i_open_a_single_session() {
        sessionsPage.clickFirstSessionId();
    }
    @Then("I should see that the NAME from the table")
    public void i_should_see_that_the_name_from_the_table() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the FULL NAME \\(OCR) value inside the session are exactly the same")
    public void the_full_name_ocr_value_inside_the_session_are_exactly_the_same() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}