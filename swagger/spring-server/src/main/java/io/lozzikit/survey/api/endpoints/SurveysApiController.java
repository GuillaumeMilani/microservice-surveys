package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveysApi;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SurveysApiController implements SurveysApi {
    @Autowired
    SurveyService surveyService;

    @Override
    public ResponseEntity<List<Survey>> getSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();

        return ResponseEntity.ok(surveys);
    }
}
