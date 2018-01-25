package io.lozzikit.survey.repositories;

import io.lozzikit.survey.entities.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface EventRepository extends MongoRepository<EventEntity, String> {
    List<EventEntity> findBySurveyId(String surveyId);
}
