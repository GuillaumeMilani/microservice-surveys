package io.lozzikit.survey.services;

import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.entities.SurveyEntity;
import io.lozzikit.survey.repositories.SurveyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    public void saveSurvey(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setOwner(survey.getOwner());
        surveyEntity.setCreatedAt(DateTime.now());

        surveyRepository.save(surveyEntity);
    }

    public Survey getSurvey(long id) {
        SurveyEntity surveyEntity = surveyRepository.findOne(id);
        Survey survey = new Survey();

        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setOwner(surveyEntity.getOwner());
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        return survey;
    }
}
