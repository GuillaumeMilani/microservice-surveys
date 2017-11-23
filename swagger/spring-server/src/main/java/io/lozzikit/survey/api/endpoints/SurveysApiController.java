package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveysApi;
import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.services.SurveyService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class SurveysApiController implements SurveysApi {
    @Autowired
    SurveyService surveyService;

    @Override
    public ResponseEntity<List<Survey>> getSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();

        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addSurvey(@Valid @RequestBody Survey body) {
        String newSurveyId = surveyService.saveSurvey(body);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSurveyId).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Survey> getSurveyById(@ApiParam(value = "ID of survey to return", required = true) @PathVariable("surveyId") String surveyId) {
        try {
            Survey survey = surveyService.getSurvey(surveyId);

            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
