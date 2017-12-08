package io.lozzikit.survey.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Status;
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

        // TODO Post answer
        throw new PendingException();
    }
}
