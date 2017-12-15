Feature: Update a Survey

  Background:
    Given there is a Surveys server
    And I have a survey with the mandatory properties set
    And I know a survey id

  Scenario: update a survey (draft -> opened) : OK
    When I set the survey to OPENED
    Then I receive a 200 status code
    And I GET it from the /survey/ID endpoint
    And The survey has the status OPENED

  Scenario: update a survey (draft -> opened -> closed) : OK
    When I set the survey to OPENED
    When I set the survey to CLOSED
    Then I receive a 200 status code
    And I GET it from the /survey/ID endpoint
    And The survey has the status CLOSED

  Scenario: update a survey (draft -> draft) : FAIL
    When I set the survey to DRAFT
    Then I receive a 400 status code

  Scenario: update a survey (draft -> closed) : FAIL
    When I set the survey to CLOSED
    Then I receive a 400 status code

  Scenario: update a survey (draft -> opened -> draft) : FAIL
    When I set the survey to OPENED
    When I set the survey to DRAFT
    Then I receive a 400 status code

  Scenario: send an incorrect status : FAIL
    When I custom PATCH an incorrect status
    Then I receive a 400 status code