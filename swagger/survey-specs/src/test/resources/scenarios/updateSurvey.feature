Feature: Update a Survey

  Background:
    Given there is a Surveys server

  Scenario: update a survey (draft -> opened) : OK
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I set the survey to OPENED
    Then I receive a 200 status code

  Scenario: update a survey (draft -> opened -> closed) : OK
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I set the survey to OPENED
    When I set the survey to CLOSED
    Then I receive a 200 status code

  Scenario: update a survey (draft -> draft) : FAIL
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I set the survey to DRAFT
    Then I receive a 400 status code

  Scenario: update a survey (draft -> closed) : FAIL
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I set the survey to CLOSED
    Then I receive a 400 status code

  Scenario: update a survey (draft -> opened -> draft) : FAIL
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I set the survey to OPENED
    When I set the survey to DRAFT
    Then I receive a 400 status code

