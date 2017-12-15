package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.ExhaustiveSurvey;
import io.lozzikit.survey.api.dto.Status;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.spec.helpers.HTTPRequest;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxime Guillod
 */
public class UpdateSurveySteps extends SurveySteps {

    private ApiResponse apiResponse;

    public UpdateSurveySteps(Environment environment) {
        super(environment);
    }

    @When("^I set the survey to (OPENED|CLOSED|DRAFT)$")
    public void iSetTheSurveyToOPENED(String status) throws Throwable {
        try {
            lastApiResponse = api.changeSurveysStatusWithHttpInfo(environment.getLastId(), Status.valueOf(status));
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

    @When("^I custom PATCH an incorrect status$")
    public void iSetTheSurveyToAnIncorrectStatus() throws IOException {
        String payload = "THIS IS AN INCORRECT STATUS";
        CloseableHttpResponse response = HTTPRequest.sendPatchRequest(api.getApiClient().getBasePath() + "/surveys/" + environment.getLastId() + "/status", payload);
        environment.setLastStatusCode(response.getStatusLine().getStatusCode());
        response.close();
    }

    @And("^The survey has the status (OPENED|CLOSED|DRAFT)$")
    public void theSurveyHasTheStatus(String status) {
        ExhaustiveSurvey survey = environment.getExhaustiveSurvey();

        assertEquals(Status.valueOf(status), survey.getStatus());
    }
}
