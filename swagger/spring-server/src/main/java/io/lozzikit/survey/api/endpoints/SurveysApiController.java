package io.lozzikit.survey.api.endpoints;

import io.lozzikit.survey.api.SurveysApi;
import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.*;
import io.lozzikit.survey.exceptions.FirstQuestionNotZeroException;
import io.lozzikit.survey.exceptions.WrongQuestionNumbersException;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class SurveysApiController implements SurveysApi {
    @Autowired
    SurveyService surveyService;

    @Autowired
    EventService eventService;

    @Autowired
    SurveyResponsesService surveyResponsesService;

    private Link buildSelfLink(String id) {
        Link link = new Link();
        link.setRel("self");
        link.setHref(ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri().toString());

        return link;
    }

    private Link buildListLink() {
        Link link = new Link();
        link.setRel("list");

        link.setHref(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/surveys")
                .build().toUri().toString());

        return link;
    }

    private Link buildEventsLink(String id) {
        Link link = buildSelfLink(id);
        link.setRel("events");
        link.setHref(link.getHref() + "/events");

        return link;
    }

    private Link buildEventsLinkFromDetail() {
        Link link = new Link();
        link.setRel("events");

        link.setHref(ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/events").toUriString());

        return link;
    }

    @Override
    public ResponseEntity<List<ExhaustiveSurvey>> getSurveys() {

        List<Function<String, Link>> linkCreationFunctions = new ArrayList<>();
        linkCreationFunctions.add(this::buildSelfLink);
        linkCreationFunctions.add(this::buildEventsLink);

        List<ExhaustiveSurvey> surveys = surveyService.getAllSurveys(linkCreationFunctions);

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

            List<Integer> answersNumber = answers.stream().map(Answer::getQuestionNumber).collect(Collectors.toList());

            List<Integer> questionsNumber = questions.stream().map(Question::getNumber).collect(Collectors.toList());

            if (!questionsNumber.containsAll(answersNumber)) {
                return ResponseEntity.badRequest().build();
            }
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        surveyResponsesService.createResponses(body, surveyId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> addSurvey(@Valid @RequestBody NewSurvey body) {
        try {
            String newSurveyId = surveyService.createSurvey(body);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newSurveyId).toUri();

            return ResponseEntity.created(location).build();

        } catch (FirstQuestionNotZeroException e) {
            return ResponseEntity.status(460).build();
        } catch (WrongQuestionNumbersException e) {
            return ResponseEntity.status(461).build();
        }
    }

    @Override
    public ResponseEntity<Void> createSurveyEvents(@PathVariable("surveyId") String surveyId, @RequestBody NewEvent newEvent) {
        try {
            ExhaustiveSurvey survey = surveyService.getSurvey(surveyId);
            Status oldStatus = survey.getStatus();
            Status status = newEvent.getStatus();

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
        } catch (FirstQuestionNotZeroException e) {
            return ResponseEntity.status(460).build();
        } catch (WrongQuestionNumbersException e) {
            return ResponseEntity.status(461).build();
        }
    }

    @Override
    public ResponseEntity<ExhaustiveSurvey> deleteSurveyById(@PathVariable("surveyId") String surveyId) {
        if (null == surveyId) {
            return ResponseEntity.badRequest().build();
        }

        try {
            surveyService.getSurvey(surveyId);
            surveyService.deleteSurvey(surveyId);

            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ExhaustiveSurvey> getSurveyById(@ApiParam(value = "ID of survey to return", required = true) @PathVariable("surveyId") String surveyId) {
        try {
            ExhaustiveSurvey survey = surveyService.getSurvey(surveyId);

            // Add list link
            survey.getLinks().add(buildListLink());
            // Add events link
            survey.getLinks().add(buildEventsLinkFromDetail());

            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<SurveyResponses>> getSurveyResponses(@ApiParam(value = "ID of survey", required = true) @PathVariable("surveyId") String surveyId) {
        List<SurveyResponses> responses = surveyResponsesService.getAllSurveyResponses(surveyId);

        return ResponseEntity.ok(responses);
    }

    public ResponseEntity<List<Event>> getSurveyEvents(@ApiParam(value = "ID of survey to return", required = true) @PathVariable("surveyId") String surveyId) {
        return ResponseEntity.ok(eventService.getEvents(surveyId));
    }
}
