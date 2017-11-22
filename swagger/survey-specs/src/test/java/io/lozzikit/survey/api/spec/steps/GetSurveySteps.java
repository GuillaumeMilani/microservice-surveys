package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Survey;
import io.lozzikit.survey.api.spec.helpers.Environment;
import org.joda.time.DateTime;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony on 17.11.2017.
 */
public class GetSurveySteps extends SurveySteps {
    private String Id;
    private String unknownId = "-1";
    private Survey survey;

    public GetSurveySteps(Environment environment) {
        super(environment);
    }

    @Given("^I know a survey id$")
    public void iKnowASurveyId() throws Throwable {
        survey = new io.lozzikit.survey.api.dto.Survey();
        survey.setOwner(0L);
        try {
            ApiResponse lastApiResponse = api.addSurveyWithHttpInfo(survey);
            if (lastApiResponse.getStatusCode() == 201) {
                Map<String, List<String>> responseHeaders = lastApiResponse.getHeaders();
                String surveyUrl = responseHeaders.get("Location").get(0);

                String creationDate = responseHeaders.get("Last-Modified").get(0);
                survey.setCreatedAt(new DateTime(creationDate));

                String[] splittedUrl = surveyUrl.split("/");
                Id = splittedUrl[splittedUrl.length - 1];
            } else {
                throw new IllegalArgumentException("unknown response");
            }
        } catch (ApiException e) {
            Assert.assertTrue(false);
        }
    }

    @Given("^I know a unknown survey id$")
    public void iKnowASurveyIdThatIsNotUsed() {
        Id = unknownId;
    }

    @When("^I GET it to the /survey/ID endpoint$")
    public void iGETItToTheSurveyIDEndpoint() throws Throwable {
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
        assertEquals(survey, receivedSurvey);
    }
}
