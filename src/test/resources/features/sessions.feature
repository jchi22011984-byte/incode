Feature: Validate sessions

  As a QA engineer
  I want to verify that session details are consistent
  So that configuration and data mapping work as expected

  Background:
    Given I go to the base login page

  Scenario: Session NAME matches FULL NAME (OCR)
    When I log in with configured credentials
    And I navigate to the sessions page
    And I open a single session "69af44ce8d1257639725d156"
    Then I should see that the NAME from the table
    And the FULL NAME (OCR) value inside the session are exactly the same
