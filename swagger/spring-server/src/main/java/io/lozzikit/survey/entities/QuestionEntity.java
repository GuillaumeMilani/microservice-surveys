package io.lozzikit.survey.entities;

import org.springframework.data.mongodb.core.index.Indexed;

public class QuestionEntity {
    private String question;
    
    @Indexed(unique = true)
    private Integer number;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
