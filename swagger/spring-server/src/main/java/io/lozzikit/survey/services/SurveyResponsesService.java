package io.lozzikit.survey.services;

import io.lozzikit.survey.api.model.Answer;
import io.lozzikit.survey.api.model.Link;
import io.lozzikit.survey.api.model.SurveyResponses;
import io.lozzikit.survey.entities.AnswerEntity;
import io.lozzikit.survey.entities.SurveyResponsesEntity;
import io.lozzikit.survey.repositories.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SurveyResponsesService {
    @Autowired
    ResponsesRepository responsesRepository;

    public void createResponses(SurveyResponses responses, String surveyId) {
        SurveyResponsesEntity surveyResponsesEntity = DTOToEntity(responses);
        surveyResponsesEntity.setId(surveyId);

        responsesRepository.save(responses);
    }

    public List<SurveyResponses> getAllSurveyResponses(Function<String, Link> selfLinkCreation) {
        List<SurveyResponses> surveyResponsesEntities = null;
        /*
        surveyResponsesEntities = responsesRepository.findAll();

        return responsesEntities.stream()
                .map(entity -> {
                    ExhaustiveSurveyResponses survey = entityToDTO(entity);

                    survey.getLinks().add(selfLinkCreation.apply(entity.getId()));

                    return survey;
                })
                .collect(Collectors.toList());*/
        return surveyResponsesEntities;
    }


    private SurveyResponses entityToDTO(SurveyResponsesEntity responsesEntity) {
        SurveyResponses responses = new SurveyResponses();

        responses.setAnswers(responsesEntity.getAnswers().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList())
        );

        return responses;
    }

    private Answer entityToDTO(AnswerEntity answerEntity) {
        Answer answer = new Answer();
        answer.setQuestionNumber(answerEntity.getQuestionNumber());
        answer.setAnswer(answerEntity.getAnswer());

        return answer;
    }

    private SurveyResponsesEntity DTOToEntity(SurveyResponses responses) {
        SurveyResponsesEntity responsesEntity = new SurveyResponsesEntity();

        responsesEntity.setUser(responses.getUser());
        responsesEntity.setAnswers(responses.getAnswers().stream()
                .map(this::DTOToEntity)
                .collect(Collectors.toList())
        );

        return responsesEntity;
    }

    private AnswerEntity DTOToEntity(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setQuestionNumber(answer.getQuestionNumber());
        answerEntity.setAnswer(answer.getAnswer());

        return answerEntity;
    }
}
