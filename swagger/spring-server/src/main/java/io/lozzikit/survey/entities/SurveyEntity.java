package io.lozzikit.survey.entities;

import io.lozzikit.survey.api.model.Status;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 *
 * @author Maxime Guillod
 */
@Document(collection = "surveys")
public class SurveyEntity implements Serializable {

    @Id
    private String id;

    private Status status;

    private long owner;

    private String title;

    private String description;

    private DateTime createdAt;

    private List<QuestionEntity> questions;

    public String getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
}
