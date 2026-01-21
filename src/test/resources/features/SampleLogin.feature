Feature: Sample Login Feature
  As a mobile app user
  I want to be able to login to the application
  So that I can access my account

  Background:
    Given the app is launched

  @smoke @login
  Scenario: Successful login with valid credentials
    Given I am on the login screen
    When I enter username "testuser@example.com"
    And I enter password "Test@123"
    And I click on login button
    Then I should see the welcome message
    And I should be on the home screen

  @regression @login
  Scenario: Login with invalid credentials
    Given I am on the login screen
    When I enter username "invalid@example.com"
    And I enter password "WrongPassword"
    And I click on login button
    Then I should see an error message
    And I should remain on the login screen

  @smoke @login
  Scenario Outline: Login with multiple credentials
    Given I am on the login screen
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on login button
    Then I should see "<result>"

    Examples:
      | username              | password    | result          |
      | valid@example.com     | Valid@123   | welcome message |
      | invalid@example.com   | Invalid@123 | error message   |
      | empty@example.com     |             | error message   |
