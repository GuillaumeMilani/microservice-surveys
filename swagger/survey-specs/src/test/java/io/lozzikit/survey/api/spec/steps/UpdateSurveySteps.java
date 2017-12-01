package io.lozzikit.survey.api.spec.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import io.lozzikit.survey.api.spec.helpers.Environment;

/**
 * @author Maxime Guillod
 */
public class UpdateSurveySteps extends SurveySteps {

    public UpdateSurveySteps(Environment environment) {
        super(environment);
    }

    @When("^I update a specific survey$")
    public void iUpdateASpecificSurvey() throws Throwable {
        // TODO
    }
}
