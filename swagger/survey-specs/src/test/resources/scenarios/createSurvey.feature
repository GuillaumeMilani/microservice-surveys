Feature: Creation of Survey

  Background:
    Given there is a Surveys server

  Scenario: create a survey
    Given I have a survey payload
    When I POST it to the /survey endpoint
    Then I receive a 201 status code


  Scenario: create a wrong survey
    Given I have a wrong survey payload
    When I POST it to the /survey endpoint
    Then I receive a 405 status code