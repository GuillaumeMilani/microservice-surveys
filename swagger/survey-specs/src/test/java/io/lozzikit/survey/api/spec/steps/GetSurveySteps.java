package io.lozzikit.survey.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.dto.Survey;
import io.lozzikit.survey.api.spec.helpers.Environment;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony on 17.11.2017.
 */
public class GetSurveySteps {
    private Environment environment;
    private SurveyApi api;
    private String ID;
    private final String unknownID = "12345";
    private final String invalidID = "éäö";

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    public GetSurveySteps() {
        this.environment = environment;
        this.api = environment.getApi();
    }


    @Given("^I know a survey id$")
    public void iKnowASurveyID() throws Throwable {
        Survey survey = new io.lozzikit.survey.api.dto.Survey();
        try {
           ApiResponse lastApiResponse = api.addSurveyWithHttpInfo(survey);
            if(lastApiResponse.getStatusCode() == 201) {
              //  ID = ??
            } else {
                throw new IllegalArgumentException("unknown response");
            }
        } catch (ApiException e) {
            Assert.assertTrue(false);
        }
    }

    @Given("^I know a invalid survey id$")
    public void i_know_a_invalid_survey_id() {
        ID = invalidID;
    }

    @Given("^I know a unknown survey id$")
    public void i_know_a_survey_id_that_is_not_used() {
        ID = unknownID;
    }

    @When("^I GET it to the /survey/ID endpoint$")
    public void iGETItToTheSurveyIDEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getSurveyByIdWithHttpInfo(ID);
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
}
