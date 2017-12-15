package io.lozzikit.survey.api.spec.steps;

import cucumber.api.java.en.Then;
import io.lozzikit.survey.api.spec.helpers.Environment;
import org.junit.Assert;

public class CommonSteps extends SurveySteps {
    public CommonSteps(Environment environment) {
        super(environment);
    }

    @Then("^I receive a (\\d+) status code")
    public void i_receive_a_status_code(int arg1) throws Throwable {
        Assert.assertEquals(arg1, environment.getLastStatusCode());
    }
}
