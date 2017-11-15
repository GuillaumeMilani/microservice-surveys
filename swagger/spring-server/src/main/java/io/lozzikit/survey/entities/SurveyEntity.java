package io.lozzikit.survey.entities;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Entity
public class SurveyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long owner;
    private String title;
    private String description;
    private DateTime createdAt;
    private Set<QuestionEntity> questions;

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

    public Set<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionEntity> questions) {
        this.questions = questions;
    }
}
