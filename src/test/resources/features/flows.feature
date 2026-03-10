Feature: Validate flows

  As a QA engineer
  I want to verify that active flows are correctly created
  So that identity validation and face capture work as expected

  Background:
    Given I go to the base login page
    And I log in with configured credentials

  Scenario: Create new active flow with required modules
    When I navigate to the flows page
    And I create a new active flow
    And I close the navigation pane
    And I add the module "Autenticación Facial"
    And I save the changes
    Then I should see that the flow "New flow" is present in the list
    And I delete the flow "New flow"