Feature: Get a Survey

  Background:
    Given there is a Surveys server

  Scenario: get a known survey with valid URL
    Given I know a survey id
    When I GET it to the /survey/ID endpoint
    Then I receive a 200 status code
    And I receive the correct survey

  Scenario: get a survey with valid URL not created
    Given I know a unknown survey id
    When I GET it to the /survey/ID endpoint
    Then I receive a 404 status code