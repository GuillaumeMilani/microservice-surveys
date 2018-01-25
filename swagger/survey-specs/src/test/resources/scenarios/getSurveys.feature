Feature: Get a Survey

  Background:
    Given there is a Surveys server

  Scenario: get surveys with no survey posted
    Given I delete all surveys
    When I GET it from the /surveys endpoint
    Then I receive a 200 status code
    And I receive no survey

  Scenario: get surveys where 1 survey is posted
    Given I know how many survey is on the server
    And I have a survey with the mandatory properties set
    And I POST its payload to the /survey endpoint
    When I GET it from the /surveys endpoint
    Then I receive a 200 status code
    And I receive the posted survey

  Scenario: get surveys where multiple surveys are posted
    Given I know how many survey is on the server
    And I have many surveys with the mandatory properties set
    And I post them to the /survey endpoint
    When I GET it from the /surveys endpoint
    Then I receive a 200 status code
    And i receive the posted surveys

