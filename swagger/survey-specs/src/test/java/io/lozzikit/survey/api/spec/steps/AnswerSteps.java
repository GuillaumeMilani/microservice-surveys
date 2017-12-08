package io.lozzikit.survey.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Answer;
import io.lozzikit.survey.api.dto.ExhaustiveSurvey;
import io.lozzikit.survey.api.dto.Status;
import io.lozzikit.survey.api.dto.SurveyResponses;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.spec.helpers.HTTPRequest;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

/**
 * @author Maxime Guillod
 */
public class AnswerSteps extends SurveySteps {

    private ApiResponse apiResponse;

    public AnswerSteps(Environment environment) {
        super(environment);
    }

    @When("^I post an answer$")
    public void iPostAnAnswer() throws Throwable {
        String id = environment.getLastId();

        // TODO
        throw new PendingException();
    }

    @And("^The answer is corrected stored$")
    public void theAnswerIsCorrectedStored() throws Throwable {

        // TODO
        throw new PendingException();
    }

    @When("^I post a malformed anser$")
    public void iPostAMalformedAnser() throws Throwable {

        // TODO
        throw new PendingException();
    }
}
