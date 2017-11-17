package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.dto.Survey;
import org.junit.Assert;

/**
 * Created by Tony on 16.11.2017.
 */
public class CreateSurveySteps {

    private Environment environment;
    private SurveyApi api;
    private Survey survey;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public CreateSurveySteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("^there is a Surveys server$")
    public void there_is_a_Surveys_server() throws Throwable {
        Assert.assertNotNull(api);

    }

    @Given("^I have a survey payload$")
    public void i_have_a_survey_payload() {
        survey = new io.lozzikit.survey.api.dto.Survey();
    }

    @When("^I POST it to the /survey endpoint$")
    public void i_POST_it_to_the_survey_endpoint() {
        try {

            lastApiResponse = api.addSurveyWithHttpInfo(survey);
            lastApiCallThrewException = false;
            lastApiException = null;
            lastStatusCode = lastApiResponse.getStatusCode();
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            lastStatusCode = lastApiException.getCode();
        }
    }

    @Then("^I receive a (\\d+) status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        if(survey == null) {
            Assert.assertEquals(405, lastStatusCode);
        } else {
            Assert.assertEquals(201, lastStatusCode);
        }
    }

    @Given("^I have a wrong survey payload$")
    public void i_have_a_wrong_survey_payload() {
        survey = null;
    }
}
