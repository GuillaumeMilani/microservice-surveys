package io.lozzikit.survey.api.spec.steps.events;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.ExhaustiveSurvey;
import io.lozzikit.survey.api.dto.NewEvent;
import io.lozzikit.survey.api.dto.Status;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.spec.helpers.HTTPRequest;
import io.lozzikit.survey.api.spec.steps.surveys.SurveySteps;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxime Guillod
 */
public class CreateStatusEventSteps extends SurveySteps {

    private ApiResponse apiResponse;

    public CreateStatusEventSteps(Environment environment) {
        super(environment);
    }

    @When("^I set the survey to (OPENED|CLOSED|DRAFT)$")
    public void iSetTheSurveyToOPENED(String status) throws Throwable {
        try {
            NewEvent newEvent = new NewEvent();
            newEvent.setStatus(Status.valueOf(status));
            lastApiResponse = api.createSurveyEventsWithHttpInfo(environment.getLastId(), newEvent);
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

    @When("^I custom POST an incorrect status$")
    public void iSetTheSurveyToAnIncorrectStatus() throws IOException {
        String payload = "{ status: \"THIS IS AN INCORRECT STATUS\" }";
        CloseableHttpResponse response = HTTPRequest.sendPostRequest(api.getApiClient().getBasePath() + "/surveys/" + environment.getLastId() + "/events", payload, ContentType.APPLICATION_JSON);
        environment.setLastStatusCode(response.getStatusLine().getStatusCode());
        response.close();
    }

    @And("^The survey has the status (OPENED|CLOSED|DRAFT)$")
    public void theSurveyHasTheStatus(String status) {
        ExhaustiveSurvey survey = environment.getExhaustiveSurvey();

        assertEquals(Status.valueOf(status), survey.getStatus());
    }
}
