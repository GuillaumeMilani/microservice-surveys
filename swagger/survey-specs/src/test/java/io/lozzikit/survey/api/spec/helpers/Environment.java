package io.lozzikit.survey.api.spec.helpers;

import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.dto.Answer;
import io.lozzikit.survey.api.dto.Event;
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
    private Answer newAnswer;
    private int numberOfAddedSurvey = 0;
    private List<Event> events;

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

    public Answer getNewAnswer() {
        return newAnswer;
    }

    public void setNewAnswer(Answer newAnswer) {
        this.newAnswer = newAnswer;
    }

    public List<ExhaustiveSurvey> getExhaustiveSurveys() {
        return exhaustiveSurveys;
    }

    public void setExhaustiveSurveys(List<ExhaustiveSurvey> exhaustiveSurveys) {
        this.exhaustiveSurveys = exhaustiveSurveys;
    }

    public List<NewSurvey> getNewSurveys() {
        return newSurveys;
    }

    public void setNewSurveys(List<NewSurvey> newSurveys) {
        this.newSurveys = newSurveys;
    }

    public int getNumberOfAddedSurvey() {
        return numberOfAddedSurvey;
    }

    public void setNumberOfAddedSurvey(int numberOfAddedSurvey) {
        this.numberOfAddedSurvey = numberOfAddedSurvey;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
