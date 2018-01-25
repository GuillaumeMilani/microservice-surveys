import {Component, Input, OnInit} from '@angular/core';


import {NewSurvey, Question, SurveyService} from '../../../shared';

import {Location} from '@angular/common';

@Component({
    selector: 'app-survey-form',
    templateUrl: './survey-form.component.html',
    styleUrls: ['./survey-form.component.scss']
})
export class SurveyFormComponent implements OnInit {
    survey: NewSurvey = new NewSurvey();

    constructor(protected surveyService: SurveyService,
                protected location: Location) {
    }

    ngOnInit() {
    }

    @Input()
    setSurvey(survey: NewSurvey): void {
        this.survey = survey;
    }

    addQuestion(): void {
        let newQuestion: Question = new Question();
        let lastNumber: number = 0;

        if (this.survey.questions.length) {
            lastNumber = Math.max.apply(Math, this.survey.questions.map(q => q.number));
        }

        newQuestion.number = lastNumber + 1;
        this.survey.questions.push(newQuestion);
    }

    removeQuestion(questionNumber: number): void {
        this.survey.questions = this.survey.questions.filter(q => q.number != questionNumber);
    }

    updateQuestionNumbers(): void {
        this.survey.questions.forEach((question, index) => question.number = index + 1);
    }

    goBack(): void {
        this.location.back();
    }

    save(survey: NewSurvey): void {
        this.surveyService.addSurvey(survey).subscribe(newSurvey => null);
        this.goBack();
    }
}
