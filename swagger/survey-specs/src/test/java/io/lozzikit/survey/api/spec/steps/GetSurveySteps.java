package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.dto.ExhaustiveSurvey;
import io.lozzikit.survey.api.dto.NewSurvey;
import io.lozzikit.survey.api.spec.helpers.Environment;
import org.junit.Assert;

import java.lang.reflect.Method;
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

    private NewSurvey exhaustiveToNewSurvey(ExhaustiveSurvey exhaustiveSurvey) {
        NewSurvey newSurvey = new NewSurvey();

        return newSurvey;
    }

    /**
     * Set the id into environment.lastid
     *
     * @throws Throwable
     */
    @Given("^I know a survey id$")
    public void iKnowASurveyId() throws Throwable {
        try {
            ApiResponse lastApiResponse = api.addSurveyWithHttpInfo(environment.getNewSurvey());
            if (lastApiResponse.getStatusCode() == 201) {
                Map<String, List<String>> responseHeaders = lastApiResponse.getHeaders();
                String surveyUrl = responseHeaders.get("Location").get(0);

                String[] splittedUrl = surveyUrl.split("/");
                Id = splittedUrl[splittedUrl.length - 1];
                environment.setLastId(Id);
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
        ExhaustiveSurvey receivedSurvey = (ExhaustiveSurvey) lastApiResponse.getData();

        // Compare only common properties

        NewSurvey newSurvey = environment.getNewSurvey();
        Class className = newSurvey.getClass();

        for (Method method : className.getDeclaredMethods()) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                Method receivedSurveyMethod = receivedSurvey.getClass().getMethod(method.getName());
                assertEquals(method.invoke(newSurvey), receivedSurveyMethod.invoke(receivedSurvey));
            }
        }
    }

    /**
     * a a wrong id into environment.lastId
     *
     * @throws Throwable
     */
    @Given("^I have a wrong id$")
    public void iHaveAWrongId() throws Throwable {
        environment.setLastId("thisIsAWrongSurveyId");
    }
}
