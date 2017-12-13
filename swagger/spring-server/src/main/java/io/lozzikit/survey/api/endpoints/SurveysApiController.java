package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveysApi;
import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.*;
import io.lozzikit.survey.services.EventService;
import io.lozzikit.survey.services.SurveyResponsesService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveysApiController implements SurveysApi {
    @Autowired
    SurveyService surveyService;

    @Autowired
    EventService eventService;

    @Autowired
    SurveyResponsesService surveyResponsesService;

    private String buildSelfLink(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri().toString();
    }

    @Override
    public ResponseEntity<List<ExhaustiveSurvey>> getSurveys() {

        List<ExhaustiveSurvey> surveys = surveyService.getAllSurveys(this::buildSelfLink);

        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postSurveysResponses(@PathVariable("surveyId") String surveyId, @Valid @RequestBody SurveyResponses body) {
        try {
            ExhaustiveSurvey exhaustiveSurvey = surveyService.getSurvey(surveyId);
            Status status = exhaustiveSurvey.getStatus();

            if (!status.equals(Status.OPENED)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            List<Question> questions = exhaustiveSurvey.getQuestions();
            List<Answer> answers = body.getAnswers();

            if (answers.size() != questions.size()) {
                return ResponseEntity.badRequest().build();
            }

            List<Integer> answersNumber = new ArrayList<>(answers.size());
            for (Answer a : answers) {
                answersNumber.add(a.getQuestionNumber());
            }

            List<Integer> questionsNumber = new ArrayList<>(questions.size());
            for (Question q : questions) {
                questionsNumber.add(q.getNumber());
            }

            if (!questionsNumber.containsAll(answersNumber)) {
                return ResponseEntity.badRequest().build();
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        surveyResponsesService.createResponses(body);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> addSurvey(@Valid @RequestBody NewSurvey body) {
        String newSurveyId = surveyService.createSurvey(body);

        // TODO: Ensure question number uniqueness. Has to be done programmatically (no way with Mongo)

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newSurveyId).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> changeSurveysStatus(@PathVariable("surveyId") String surveyId, @RequestBody Status status) {
        try {
            ExhaustiveSurvey survey = surveyService.getSurvey(surveyId);
            Status oldStatus = survey.getStatus();

            // Update status if value changed
            // The following status changes are forbidden : closed -> any, open -> draft, draft -> closed
            if (status.equals(oldStatus)
                    || oldStatus.equals(Status.CLOSED)
                    || (oldStatus.equals(Status.OPENED) && status.equals(Status.DRAFT))
                    || (oldStatus.equals(Status.DRAFT) && status.equals(Status.CLOSED))
                    ) {
                return ResponseEntity.badRequest().build();
            }

            Event event = new Event();
            event.setSurveyId(surveyId);
            event.setStatus(status);

            eventService.saveEvent(event);

            survey.setStatus(status);
            surveyService.updateSurvey(survey, surveyId);

            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ExhaustiveSurvey> getSurveyById(@ApiParam(value = "ID of survey to return", required = true) @PathVariable("surveyId") String surveyId) {
        try {
            ExhaustiveSurvey survey = surveyService.getSurvey(surveyId);

            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
