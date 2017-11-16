package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveyApi;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class SurveyApiController implements SurveyApi {
    @Autowired
    SurveyService surveyService;

    @Override
    public ResponseEntity<Void> addSurvey(Survey body) {
        surveyService.saveSurvey(body);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Survey> getSurveyById(String surveyId) {
        Survey survey = surveyService.getSurvey(surveyId);

        if (survey == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(survey);
        }
    }
}
