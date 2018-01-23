import { Component, OnInit, Input } from '@angular/core';

import { ExhaustiveSurvey, Question, User } from '../../../shared';

import { Location } from '@angular/common';

import { SurveyService, SessionService } from '../../../shared';

@Component({
  selector: 'app-survey-detail',
  templateUrl: './survey-detail.component.html',
  styleUrls: ['./survey-detail.component.scss']
})
export class SurveyDetailComponent implements OnInit {
  survey: ExhaustiveSurvey = new ExhaustiveSurvey();

  constructor(
    private surveyService: SurveyService,
    private location: Location,
    private sessionService: SessionService,
  ) { }

  ngOnInit() {
    this.getSurvey();
  }

  getSurvey(): void {
    this.surveyService.getSurvey(this.sessionService.getDetailUrl()).subscribe(survey => this.survey = survey);
  }

  addQuestion(): void {
    let newQuestion:Question = new Question();
    let lastNumber:number = Math.max.apply(Math, this.survey.questions.map(q => q.number));

    newQuestion.number = lastNumber + 1;    
    this.survey.questions.push(newQuestion);
  }

  removeQuestion(questionNumber: number): void {
    this.survey.questions = this.survey.questions.filter(q => q.number != questionNumber);
  }

  goBack(): void {
    this.location.back();
  }
}
