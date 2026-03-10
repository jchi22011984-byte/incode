package com.incode.qa.steps;

import com.incode.qa.ui.DashboardPage;
import com.incode.qa.ui.FlowsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlowSteps {

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private FlowsPage flowsPage;

    @When("I navigate to the flows page")
    public void iNavigateToTheFlowsPage() {
        dashboardPage.goToFlowsPage();
    }

    @And("I create a new active flow")
    public void iCreateANewActiveFlowNamed() {
        flowsPage.createNewActiveFlow();
    }

    @And("I close the navigation pane")
    public void iCloseTheNavPane() {
        flowsPage.closeNav();
    }

    @And("I add the module {string}")
    public void iAddTheModule(String moduleName) {
        flowsPage.addModuleToFlow(moduleName);
    }

    @And("I save the changes")
    public void iSaveTheChanges() {
        flowsPage.saveChanges();
    }

    @And("I wait")
    public void iWait() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
        }
    }

    @Then("I should see that the flow {string} is present in the list")
    public void iShouldSeeThatTheFlowIsPresentInTheList(String flowName) {
        assertTrue(flowsPage.isFlowPresent(flowName),
                "Expected flow " + flowName + " to be present in the list");
    }

    @And("I delete the flow {string}")
    public void iDeleteTheFlow(String flowName) {
        flowsPage.deleteFlow(flowName);
    }
}