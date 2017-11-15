package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.repositories.SurveyRepository;
import io.lozzikit.survey.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SurveyApiController implements SurveyApi {
    @Autowired
    SurveyService surveyService;

    @Override
    public ResponseEntity<Void> addSurvey(Survey body) {
        surveyService.saveSurvey(body);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Survey> getSurveyById(Long surveyId) {
        return null;
    }
}
