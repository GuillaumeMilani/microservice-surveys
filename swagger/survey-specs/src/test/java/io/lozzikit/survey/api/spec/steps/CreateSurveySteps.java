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

    @When("^I POST its payload to the /survey endpoint$")
    public void iPOSTItsPayloadToTheSurveyEndpoint() {
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

    @Given("^I have a survey payload without owner$")
    public void iHaveASurveyPayloadWithoutOwner() throws Throwable {
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

    @Given("^I have a survey payload with wrong owner type$")
    public void iHaveAPayloadWithWrongOwnerType() throws Throwable {
        payload = "{\n" +
                "  \"owner\": \"THIS IS AN INVALID OWNER ID\",\n" +
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
        HTTPRequest.HTTPResponse response = HTTPRequest.sendPostRequest(api.getApiClient().getBasePath() + "/surveys", payload, contentType);
        Logger log = Logger.getLogger("Create Survey Step");
        log.log(Level.SEVERE, response.getContent());
        environment.setLastStatusCode(response.getStatusCode());
    }

    @Given("^I have a survey with only the owner property set$")
    public void iHaveASurveyWithAnOwnerPayload() throws Throwable {
        survey = new io.lozzikit.survey.api.dto.Survey();
        survey.setOwner((long) 1);
    }
}
