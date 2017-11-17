package io.lozzikit.survey.api.spec.helpers;

import io.lozzikit.survey.api.SurveyApi;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private SurveyApi api = new SurveyApi();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.lozzikit.surveys.server.url");
        api.getApiClient().setBasePath(url);

    }

    public SurveyApi getApi() {
        return api;
    }


}
