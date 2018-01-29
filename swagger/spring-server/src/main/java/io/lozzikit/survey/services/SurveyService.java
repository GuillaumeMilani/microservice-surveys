package io.lozzikit.survey.services;

import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.*;
import io.lozzikit.survey.entities.QuestionEntity;
import io.lozzikit.survey.entities.SurveyEntity;
import io.lozzikit.survey.entities.UserEntity;
import io.lozzikit.survey.exceptions.FirstQuestionNotZeroException;
import io.lozzikit.survey.exceptions.WrongQuestionNumbersException;
import io.lozzikit.survey.repositories.SurveyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SurveyService {
    @Autowired
    SurveyRepository surveyRepository;

    public List<ExhaustiveSurvey> getAllSurveys(List<Function<String, Link>> linkCreationFunctions) {
        List<SurveyEntity> surveyEntities = surveyRepository.findAll();

        return surveyEntities.stream()
                .map(entity -> {
                    ExhaustiveSurvey survey = entityToDTO(entity);

                    linkCreationFunctions.forEach(function -> survey.getLinks().add(function.apply(entity.getId())));

                    return survey;
                })
                .collect(Collectors.toList());
    }

    public String createSurvey(NewSurvey survey) throws FirstQuestionNotZeroException, WrongQuestionNumbersException {
        checkQuestions(survey.getQuestions());

        SurveyEntity surveyEntity = DTOToEntity(survey);

        surveyRepository.save(surveyEntity);
        return surveyEntity.getId();
    }

    public void updateSurvey(ExhaustiveSurvey survey, String id) throws FirstQuestionNotZeroException, WrongQuestionNumbersException {
        checkQuestions(survey.getQuestions());

        SurveyEntity surveyEntity = DTOToEntity(survey);
        surveyEntity.setId(id);

        surveyRepository.save(surveyEntity);
    }

    public ExhaustiveSurvey getSurvey(String id) throws NotFoundException {
        SurveyEntity surveyEntity = surveyRepository.findOne(id);

        if (null == surveyEntity) {
            throw new NotFoundException(404, "Survey not found");
        }

        return entityToDTO(surveyEntity);
    }

    public void deleteSurvey(String id) {
        surveyRepository.delete(id);
    }

    private void checkQuestions(List<Question> questions) throws FirstQuestionNotZeroException, WrongQuestionNumbersException {
        for (Question q : questions) {
            if (q.getNumber() == null) {
                throw new WrongQuestionNumbersException();
            }
        }

        List<Integer> questionNumbers = questions.stream().map(Question::getNumber).sorted(Integer::compare).collect(Collectors.toList());

        Iterator<Integer> iterator = questionNumbers.iterator();

        if (iterator.hasNext() && iterator.next() != 0) {
            throw new FirstQuestionNotZeroException();
        }

        Integer previous = 0;
        while (iterator.hasNext()) {
            Integer current = iterator.next();
            if (current != previous + 1) {
                throw new WrongQuestionNumbersException();
            }
            previous = current;
        }
    }

    private ExhaustiveSurvey entityToDTO(SurveyEntity surveyEntity) {
        ExhaustiveSurvey survey = new ExhaustiveSurvey();

        survey.setTitle(surveyEntity.getTitle());
        survey.setStatus(surveyEntity.getStatus());
        survey.setDescription(surveyEntity.getDescription());
        survey.setUser(entityToDTO(surveyEntity.getUser()));
        survey.setDatetime(surveyEntity.getCreatedAt());
        survey.setQuestions(surveyEntity.getQuestions().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList())
        );

        survey.setLinks(new LinkedList<>());

        return survey;
    }

    private User entityToDTO(UserEntity userEntity) {
        User user = new User();
        user.setUsername(userEntity.getUsername());

        return user;
    }

    private Question entityToDTO(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setQuestion(questionEntity.getQuestion());
        question.setNumber(questionEntity.getNumber());

        return question;
    }

    private SurveyEntity DTOToEntity(ExhaustiveSurvey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();

        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setStatus(survey.getStatus());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setUser(DTOToEntity(survey.getUser()));
        surveyEntity.setCreatedAt(survey.getDatetime());
        surveyEntity.setQuestions(survey.getQuestions().stream()
                .map(this::DTOToEntity)
                .collect(Collectors.toList())
        );

        return surveyEntity;
    }

    private SurveyEntity DTOToEntity(NewSurvey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();

        // Server-set properties
        surveyEntity.setStatus(Status.DRAFT);
        surveyEntity.setCreatedAt(DateTime.now());

        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setUser(DTOToEntity(survey.getUser()));
        surveyEntity.setQuestions(survey.getQuestions().stream()
                .map(this::DTOToEntity)
                .collect(Collectors.toList())
        );

        return surveyEntity;
    }

    private UserEntity DTOToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());

        return userEntity;
    }

    private QuestionEntity DTOToEntity(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(question.getQuestion());
        questionEntity.setNumber(question.getNumber());

        return questionEntity;
    }
}
