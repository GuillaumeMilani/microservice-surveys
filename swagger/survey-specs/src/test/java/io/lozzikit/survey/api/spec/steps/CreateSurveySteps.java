package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.Survey;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.spec.helpers.HTTPRequest;
import org.junit.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tony on 16.11.2017.
 */
public class CreateSurveySteps extends SurveySteps {
    private Survey survey;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;

    private String payload;
    private String contentType = "application/json";

    public CreateSurveySteps(Environment environment) {
        super(environment);
    }

    @Given("^there is a Surveys server$")
    public void there_is_a_Surveys_server() throws Throwable {
        Assert.assertNotNull(api);
    }

    @Given("^I have an empty survey payload$")
    public void i_have_a_survey_payload() {
        survey = new io.lozzikit.survey.api.dto.Survey();
    }

    @When("^I POST it to the /survey endpoint$")
    public void i_POST_it_to_the_survey_endpoint() {
        try {

            lastApiResponse = api.addSurveyWithHttpInfo(survey);
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

    @Given("^I have a wrong body survey payload$")
    public void iHaveAWrongBodySurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a wrong owner survey payload$")
    public void iHaveAWrongOwnerSurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"owner\": \"asd\",\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a int title survey payload$")
    public void iHaveAWrongTitleSurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"owner\": 1,\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": 1234,\n" +
                "  \"description\": \"string\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a int description survey payload$")
    public void iHaveAWrongDescriptionSurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"owner\": 1,\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": 1234,\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a int created survey payload$")
    public void iHaveAWrongCreatedSurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"owner\": 1,\n" +
                "  \"createdAt\": \"201717T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a int questions survey payload$")
    public void iHaveAWrongQuestionsSurveyPayload() throws Throwable {
        payload = "{\n" +
                "  \"owner\": 1,\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": 1234\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @Given("^I have a wrong content type survey payload$")
    public void iHaveAWrongContentTypeSurveyPayload() throws Throwable {
        contentType = "text/plain";
        payload = "{\n" +
                "  \"owner\": 1,\n" +
                "  \"createdAt\": \"2017-11-17T14:38:21.677Z\",\n" +
                "  \"status\": \"draft\",\n" +
                "  \"title\": \"string\",\n" +
                "  \"description\": \"asdf\",\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"string\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @When("^I custom POST it to the /survey endpoint$")
    public void iCustomPOSTItToTheSurveyEndpoint() throws Throwable {
        HTTPRequest.HTTPResponse response = HTTPRequest.sendPostRequest(api.getApiClient().getBasePath() + "/surveys",payload,contentType);
        Logger log = Logger.getLogger("Create Survey Step");
        log.log(Level.SEVERE, response.getContent());
        environment.setLastStatusCode(response.getStatusCode());
    }

    @Given("^I have a survey with an owner payload$")
    public void iHaveASurveyWithAnOwnerPayload() throws Throwable {
        survey = new io.lozzikit.survey.api.dto.Survey();
        survey.setOwner((long) 1);
    }
}
