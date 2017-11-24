package io.lozzikit.survey.services;

import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.Question;
import io.lozzikit.survey.api.model.Survey;
import io.lozzikit.survey.entities.QuestionEntity;
import io.lozzikit.survey.entities.SurveyEntity;
import io.lozzikit.survey.repositories.SurveyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    public List<Survey> getAllSurveys() {
        List<SurveyEntity> surveyEntities = surveyRepository.findAll();

        return surveyEntities.stream()
                .map(this::entityToSurvey)
                .collect(Collectors.toList());
    }

    public String saveSurvey(Survey survey) {
        survey.setDatetime(DateTime.now());

        SurveyEntity surveyEntity = surveyToEntity(survey);

        surveyRepository.save(surveyEntity);
        return surveyEntity.getId();
    }

    public Survey getSurvey(String id) throws NotFoundException {
        SurveyEntity surveyEntity = surveyRepository.findOne(id);

        if (null == surveyEntity) {
            throw new NotFoundException(404, "Survey not found");
        }

        return entityToSurvey(surveyEntity);
    }

    private SurveyEntity surveyToEntity(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setOwner(survey.getUser());
        surveyEntity.setCreatedAt(survey.getDatetime());
        surveyEntity.setQuestions(survey.getQuestions().stream()
                .map(this::questionToEntity)
                .collect(Collectors.toList())
        );

        return surveyEntity;
    }

    private Survey entityToSurvey(SurveyEntity surveyEntity) {
        Survey survey = new Survey();

        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setUser(surveyEntity.getOwner());
        survey.setDatetime(surveyEntity.getCreatedAt());
        survey.setQuestions(surveyEntity.getQuestions().stream()
                .map(this::entityToQuestion)
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
