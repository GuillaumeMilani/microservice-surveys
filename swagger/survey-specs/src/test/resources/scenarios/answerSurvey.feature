Feature: Answer a Survey

  Background:
    Given there is a Surveys server
    And I have a survey with the mandatory properties set
    And I know a survey id

  Scenario: answer a Survey
    When I post an answer
    Then I receive a 200 status code