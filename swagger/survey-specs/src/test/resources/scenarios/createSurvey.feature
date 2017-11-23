Feature: Creation of Survey

  Background:
    Given there is a Surveys server

  Scenario: create a survey
    Given I have an empty survey payload
    When I POST its payload to the /survey endpoint
    Then I receive a 400 status code

  Scenario: create a survey
    Given I have a survey with only the owner property set
    When I POST its payload to the /survey endpoint
    Then I receive a 201 status code

  Scenario: create a survey with wrong content type payload
    Given I have a wrong content type survey payload
    When I custom POST it to the /survey endpoint
    Then I receive a 415 status code

  Scenario: create a wrong survey with wrong body
    Given I have a survey payload without owner
    When I custom POST it to the /survey endpoint
    Then I receive a 400 status code

  Scenario: create a survey with wrong owner type
    Given I have a survey payload with wrong owner type
    When I custom POST it to the /survey endpoint
    Then I receive a 400 status code