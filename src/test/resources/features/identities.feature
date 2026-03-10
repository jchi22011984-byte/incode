
Feature: Validate identities

  As a QA engineer
  I want to verify that identities are correctly created
  So that face enrollment and mapping work as expected

  Background:
    Given I go to the base login page
    And I log in with configured credentials

  Scenario: Identity is created after adding face
    When I navigate to the sessions page
    And I open a single session "69af652b0bc67e1b16f0e30a"
    And I click on Add face to database
    And I navigate to the identities page
    Then I should see that the identity is present in the list "69af54ae1eb2441830b5bab8"
