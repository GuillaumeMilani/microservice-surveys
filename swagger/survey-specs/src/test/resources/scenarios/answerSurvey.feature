Feature: Answer a Survey

  Background:
    Given there is a Surveys server
    And I have a survey with the mandatory properties set
    And I know a survey id

  Scenario: answer a survey
    When I create a correct answer
    And I post this answer
    Then I receive a 201 status code

  Scenario: answer a Survey
    When I create a correct answer
    And I post this answer
    Then I receive a 201 status code
    And The answer is corrected stored

  Scenario: answer a unknown survey
    Given I have a wrong id
    When I create a correct answer
    And I post this answer
    Then I receive a 404 status code

  Scenario: answer with malformed content
    When I create a malformed answer
    And I post this answer
    Then I receive a 400 status code