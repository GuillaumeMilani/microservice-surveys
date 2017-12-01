package io.lozzikit.survey.api.spec.helpers;

import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.dto.ExhaustiveSurvey;
import io.lozzikit.survey.api.dto.NewSurvey;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private NewSurvey newSurvey;
    private ExhaustiveSurvey exhaustiveSurvey;
    private int lastStatusCode;
    private SurveyApi api = new SurveyApi();
    private String lastId;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.lozzikit.surveys.server.url");
        api.getApiClient().setBasePath(url);

    }

    public SurveyApi getApi() {
        return api;
    }


    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public ExhaustiveSurvey getExhaustiveSurvey() {
        return exhaustiveSurvey;
    }

    public void setExhaustiveSurvey(ExhaustiveSurvey exhaustiveSurvey) {
        this.exhaustiveSurvey = exhaustiveSurvey;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public NewSurvey getNewSurvey() {
        return newSurvey;
    }

    public void setNewSurvey(NewSurvey newSurvey) {
        this.newSurvey = newSurvey;
    }
}
