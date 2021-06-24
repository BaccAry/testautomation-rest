Feature: TS002 REST API

  Scenario: TS002-TC001 Create user
    Given Create a new user
    Then Check if created user exists

  Scenario: TS002-TC002 Update user
    Given Modify a user
    Then Check if user was modified