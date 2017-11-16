package io.lozzikit.survey.entities;

import javax.persistence.Id;

public class QuestionEntity {
    @Id
    private String id;

    private String question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
