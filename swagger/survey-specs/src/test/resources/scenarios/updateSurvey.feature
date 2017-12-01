Feature: Update a Survey

  Background:
    Given there is a Surveys server

  Scenario: update a survey
    Given I have a survey with the mandatory properties set
    And I know a survey id
    When I update a specific survey
    #Then The survey will be edited
