package io.lozzikit.survey.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Answer;
import io.lozzikit.survey.api.dto.SurveyResponses;
import io.lozzikit.survey.api.spec.helpers.Environment;

/**
 * @author Maxime Guillod
 */
public class AnswerSteps extends SurveySteps {

    private ApiResponse apiResponse;

    public AnswerSteps(Environment environment) {
        super(environment);
    }

    @When("^I post this answer$")
    public void iPostThisAnswer() throws Throwable {
        String id = environment.getLastId();
        Answer answer = environment.getLastAnswer();

        SurveyResponses responses = new SurveyResponses();
        responses.addAnswersItem(answer);

        try {
            lastApiResponse = api.postSurveysResponsesWithHttpInfo(id, responses);
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

    /**
     * Put the answer into environment.lastAnswer
     *
     * @throws Throwable
     */
    @When("^I create a correct answer$")
    public void iCreateACorrectAnswer() throws Throwable {
        Answer answer = new Answer();
        answer.setQuestionNumber(0);
        answer.setAnswer("My Answer");

        environment.setLastAnswer(answer);
    }

    /**
     * Put the answer into environment.lastAnswer
     *
     * @throws Throwable
     */
    @When("^I create a malformed answer$")
    public void iCreateAMalformedAnswer() throws Throwable {
        Answer answer = new Answer();
        answer.setQuestionNumber(-1);
        answer.setAnswer(null);

        environment.setLastAnswer(answer);
    }
}
