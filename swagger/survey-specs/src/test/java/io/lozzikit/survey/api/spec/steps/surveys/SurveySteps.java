package io.lozzikit.survey.api.spec.steps.surveys;

import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.ApiResponse;
import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.spec.helpers.Environment;

public abstract class SurveySteps {
    protected Environment environment;
    protected SurveyApi api;

    protected ApiResponse lastApiResponse;
    protected ApiException lastApiException;

    protected boolean lastApiCallThrewException;

    public SurveySteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }
}
