Feature: Get a Survey

  Background:
    Given there is a Surveys server

  Scenario: get events from a new survey
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I GET its events from the status endpoint
    Then I receive a 200 status code
    And I receive no event

  Scenario: get events from a survey with one event
    Given I have a survey with the mandatory properties set
    And I know a survey id
    And I set the survey to OPENED
    When I GET its events from the status endpoint
    Then I receive a 200 status code
    And I receive 1 OPENED event

  Scenario: get events from a survey with multiple events
    Given I have a survey with the mandatory properties set
    And I know a survey id
    And I set the survey to OPENED
    And I set the survey to CLOSED
    When I GET its events from the status endpoint
    Then I receive a 200 status code
    And I receive 2 events
    And I receive 1 OPENED event
    And I receive 1 CLOSED event
