package io.lozzikit.survey.services;

import io.lozzikit.survey.api.model.Answer;
import io.lozzikit.survey.api.model.SurveyResponses;
import io.lozzikit.survey.entities.AnswerEntity;
import io.lozzikit.survey.entities.SurveyResponsesEntity;
import io.lozzikit.survey.repositories.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SurveyResponsesService {
    @Autowired
    ResponsesRepository responsesRepository;

    public void createResponses(SurveyResponses responses) {
        SurveyResponsesEntity surveyResponsesEntity = DTOToEntity(responses);

        responsesRepository.save(responses);
    }

    private SurveyResponses entityToDTO(SurveyResponsesEntity responsesEntity) {
        SurveyResponses responses = new SurveyResponses();

        responses.setSurveyId(responsesEntity.getSurveyId());
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

        responsesEntity.setSurveyId(responses.getSurveyId());
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
