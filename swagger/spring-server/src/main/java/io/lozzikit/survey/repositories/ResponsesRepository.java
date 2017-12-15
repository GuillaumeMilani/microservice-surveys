package io.lozzikit.survey.repositories;

import io.lozzikit.survey.api.model.SurveyResponses;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface ResponsesRepository extends MongoRepository<SurveyResponses, String> {

}
