package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Status;
import io.lozzikit.survey.api.spec.helpers.Environment;

/**
 * @author Maxime Guillod
 */
public class UpdateSurveySteps extends SurveySteps {

    private ApiResponse apiResponse;

    public UpdateSurveySteps(Environment environment) {
        super(environment);
    }

    private void updateSurveyStatus(Status status) throws Throwable {
        try {
            lastApiResponse = api.changeSurveysStatusWithHttpInfo(environment.getLastId(), status);
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

    @When("^I set the survey to OPENED$")
    public void iSetTheSurveyToOPENED() throws Throwable {
        updateSurveyStatus(Status.OPENED);
    }

    @When("^I set the survey to CLOSED$")
    public void iSetTheSurveyToCLOSED() throws Throwable {
        updateSurveyStatus(Status.CLOSED);
    }

    @When("^I set the survey to DRAFT")
    public void iSetTheSurveyToDRAFT() throws Throwable {
        updateSurveyStatus(Status.DRAFT);
    }
}
