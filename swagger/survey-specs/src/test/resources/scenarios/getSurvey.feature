Feature: Get a Survey

  Background:
    Given there is a Surveys server

  Scenario: get a known survey with valid id
    Given I know a survey ID
    When I GET it to the /survey/{ID} endpoint
    Then I receive a 200 status code
    And I receive the correct survey

  Scenario: get a survey with invalid id
    Given I know a invalid survey id
    When I GET it to the /survey/{ID} endpoint
    Then I receive a 400 status code

  Scenario: get a survey with valid id not created
    Given I know a survey ID that is not used
    When I GET it to the /survey/{ID} endpoint
    Then I receive a 405 status code