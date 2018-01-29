package io.lozzikit.survey.api.spec.steps.surveys;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.api.dto.Link;
import io.lozzikit.survey.api.spec.helpers.Environment;

import java.util.stream.Collectors;

/**
 * Created by Tony on 17.11.2017.
 */
public class DeleteSurveySteps extends SurveySteps {
    private String Id;

    public DeleteSurveySteps(Environment environment) {
        super(environment);
    }

    @When("^I DELETE it from the /survey/ID endpoint$")
    public void iDELETEItFromTheSurveysEndpoint() throws Throwable {
        try {
            lastApiResponse = api.deleteSurveyByIdWithHttpInfo(environment.getLastId());
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

    @And("^I delete all surveys$")
    public void iDELETEAllSurveys() throws Throwable {
        environment.getExhaustiveSurveys().forEach(survey -> {
            String url = survey.getLinks().stream().filter(link -> link.getRel().equalsIgnoreCase("self")).map(Link::getHref).collect(Collectors.toList()).get(0);

            try {
                api.deleteSurveyById(extractId(url));
            } catch (ApiException e) {
                e.printStackTrace();
            }
        });
    }
}
