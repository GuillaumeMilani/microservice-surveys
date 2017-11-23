package io.lozzikit.survey.repositories;

import io.lozzikit.survey.entities.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface SurveyRepository extends MongoRepository<SurveyEntity, String> {

}
