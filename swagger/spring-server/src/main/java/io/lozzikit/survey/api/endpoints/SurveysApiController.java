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

    @Override
    public ResponseEntity<List<ExhaustiveSurvey>> getSurveys() {
        List<ExhaustiveSurvey> surveys = surveyService.getAllSurveys();

        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postSurveysResponses(@PathVariable("surveyId") String surveyId, @Valid @RequestBody SurveyResponses body) {
        try {
            ExhaustiveSurvey exhaustiveSurvey = surveyService.getSurvey(surveyId);
            Status status = exhaustiveSurvey.getStatus();

            if (status.equals(Status.DRAFT) || status.equals(Status.CLOSED)) {
                return ResponseEntity.status(403).build();
            }

            List<Question> questions = exhaustiveSurvey.getQuestions();
            List<Answer> answers = body.getAnswers();

            if (answers.size() != questions.size()) {
                return ResponseEntity.status(400).build();
            }

            List<Integer> answersNumber = new ArrayList<>(answers.size());
            for (Answer a: answers) {
                answersNumber.add(a.getQuestionNumber());
            }

            List<Integer> questionsNumber = new ArrayList<>(questions.size());
            for (Question q: questions) {
                questionsNumber.add(q.getNumber());
            }

            // TODO Gabirel : all elements into questionNumber is null if content is correct
            if (!questionsNumber.containsAll(answersNumber)) {
                return ResponseEntity.status(400).build();
            }
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).build();
        }

        surveyResponsesService.createResponses(body);

        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Void> addSurvey(@Valid @RequestBody NewSurvey body) {
        String newSurveyId = surveyService.createSurvey(body);

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
