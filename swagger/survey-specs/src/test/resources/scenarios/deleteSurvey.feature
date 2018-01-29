Feature: Delete a Survey

  Background:
    Given there is a Surveys server

  Scenario: delete a known survey with valid URL
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I DELETE it from the /survey/ID endpoint
    Then I receive a 200 status code
    And I GET it from the /survey/ID endpoint
    And I receive a 404 status code

  Scenario: try to delete a non-existing survey
    Given I know an id that doesn't match any survey
    When I DELETE it from the /survey/ID endpoint
    Then I receive a 404 status code