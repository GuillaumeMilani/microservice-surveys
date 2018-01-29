package io.lozzikit.survey.repositories;

import io.lozzikit.survey.entities.SurveyResponsesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface ResponsesRepository extends MongoRepository<SurveyResponsesEntity, String> {
    List<SurveyResponsesEntity> findBySurveyId(String surveyId);
}
