package io.lozzikit.survey.services;

import io.lozzikit.survey.api.model.Question;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.entities.QuestionEntity;
import io.lozzikit.survey.entities.SurveyEntity;
import io.lozzikit.survey.repositories.SurveyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    public long saveSurvey(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setOwner(survey.getOwner());
        surveyEntity.setCreatedAt(DateTime.now());
        surveyEntity.setQuestions(survey.getQuestions().stream()
                .map(question -> questionToEntity(question))
                .collect(Collectors.toSet())
        );

        surveyRepository.save(surveyEntity);
        return surveyEntity.getId();
    }

    public Survey getSurvey(long id) {
        SurveyEntity surveyEntity = surveyRepository.findOne(id);
        Survey survey = new Survey();

        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setOwner(surveyEntity.getOwner());
        survey.setCreatedAt(surveyEntity.getCreatedAt());
        survey.setQuestions(surveyEntity.getQuestions().stream()
                .map(questionEntity -> entityToQuestion(questionEntity))
                .collect(Collectors.toList())
        );

        return survey;
    }

    private QuestionEntity questionToEntity(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(question.getQuestion());

        return questionEntity;
    }

    private Question entityToQuestion(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setQuestion(questionEntity.getQuestion());

        return question;
    }
}
