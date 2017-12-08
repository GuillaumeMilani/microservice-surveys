Feature: Answer a Survey

  Background:
    Given there is a Surveys server
    And I have a survey with the mandatory properties set
    And I add a question to the survey
    And I know a survey id

  Scenario: answer a survey (Draft -> FAIL)
    When I create a correct answer
    And I post this answer
    Then I receive a 403 status code

  Scenario: answer a survey (Closed -> FAIL)
    Given I set the survey to CLOSED
    When I create a correct answer
    And I post this answer
    Then I receive a 403 status code

  Scenario: answer a survey (Opened -> OK)
    Given I set the survey to OPENED
    When I create a correct answer
    And I post this answer
    Then I receive a 201 status code

  Scenario: answer a unknown survey
    Given I have a wrong id
    When I create a correct answer
    And I post this answer
    Then I receive a 404 status code

  Scenario: answer with malformed content
    Given I set the survey to OPENED
    When I create a malformed answer
    And I post this answer
    Then I receive a 400 status code