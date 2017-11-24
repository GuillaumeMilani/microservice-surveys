package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Survey;
import io.lozzikit.survey.api.spec.helpers.Environment;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony on 17.11.2017.
 */
public class GetSurveySteps extends SurveySteps {
    private String Id;

    public GetSurveySteps(Environment environment) {
        super(environment);
    }

    @Given("^I know a survey id$")
    public void iKnowASurveyId() throws Throwable {
        try {
            ApiResponse lastApiResponse = api.addSurveyWithHttpInfo(environment.getSurvey());
            if (lastApiResponse.getStatusCode() == 201) {
                Map<String, List<String>> responseHeaders = lastApiResponse.getHeaders();
                String surveyUrl = responseHeaders.get("Location").get(0);

                String[] splittedUrl = surveyUrl.split("/");
                Id = splittedUrl[splittedUrl.length - 1];
            } else {
                throw new IllegalArgumentException("unknown response");
            }
        } catch (ApiException e) {
            Assert.assertTrue(false);
        }
    }

    @Given("^I know an id that doesn't match any survey$")
    public void iKnowAnIdThatDoesntMatchAnySurvey() {
        Id = "THIS ID DOESN'T MATCH ANY SURVEY";
    }

    @When("^I GET it from the /survey/ID endpoint$")
    public void iGETItFromTheSurveyIDEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getSurveyByIdWithHttpInfo(Id);
            lastApiCallThrewException = false;
            lastApiException = null;
            environment.setLastStatusCode(lastApiResponse.getStatusCode());
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            environment.setLastStatusCode(lastApiException.getCode());
        }
    }

    @And("^I receive the correct survey$")
    public void iReceiveTheCorrectSurvey() throws Throwable {
        Survey receivedSurvey = (Survey) lastApiResponse.getData();

        // Erase the properties set by the server before doing assertEquals
        receivedSurvey.setDatetime(null);

        assertEquals(environment.getSurvey(), receivedSurvey);
    }
}
