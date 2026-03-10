package com.incode.qa.steps;

import com.incode.qa.config.ApplicationProperties;
import com.incode.qa.ui.DashboardPage;
import com.incode.qa.ui.IdentitiesPage;
import com.incode.qa.ui.SessionsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Normalizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Steps {

    @Autowired
    private DashboardPage dashboardPage;
    @Autowired
    private SessionsPage sessionsPage;
    @Autowired
    private IdentitiesPage identitiesPage;
    @Autowired
    private ApplicationProperties applicationProperties;

    private String nameFromTable;

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase()
                .trim();
    }

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

    @When("I open a single session {string}")
    public void i_open_a_single_session(String sessionId) {
        nameFromTable = sessionsPage.getFirstNameFromTable(sessionId);
        sessionsPage.clickSessionById(sessionId);
    }

    @Then("I should see that the NAME from the table")
    public void i_should_see_that_the_name_from_the_table() {
        assertNotNull("The name from the table was not captured correctly.", nameFromTable);
    }

    @Then("the FULL NAME \\(OCR) value inside the session are exactly the same")
    public void the_full_name_ocr_value_inside_the_session_are_exactly_the_same() {
        String ocrName = sessionsPage.getOcrFullNameFromDetails();

        assertEquals(
                "The name in the table does not match the OCR name in details.",
                normalize(nameFromTable),
                normalize(ocrName)
        );
    }

    @And("I click on Add face to database")
    public void iClickOnAddFaceToDatabase() {
        sessionsPage.clickAddFaceButton();
    }

    @When("I navigate to the identities page")
    public void i_navigate_to_the_identities_page() {
       sessionsPage.clickIdentitiesLink();
    }
    @Then("I should see that the identity is present in the list {string}")
    public void i_should_see_that_the_identity_is_present_in_the_list(String id) {
        identitiesPage.clickIdentityById(id);
    }
}