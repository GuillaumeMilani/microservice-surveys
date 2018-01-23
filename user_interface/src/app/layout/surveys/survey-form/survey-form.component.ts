import { Component, OnInit, Input } from '@angular/core';

import { Question, NewSurvey } from '../../../shared';

import { Location } from '@angular/common';

import { SurveyService } from '../../../shared';

@Component({
  selector: 'app-survey-form',
  templateUrl: './survey-form.component.html',
  styleUrls: ['./survey-form.component.scss']
})
export class SurveyFormComponent implements OnInit {
  survey: NewSurvey = new NewSurvey();

  constructor(
    protected surveyService: SurveyService,
    protected location: Location
  ) { }

  ngOnInit() {
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

  save(survey: NewSurvey): void {
      this.surveyService.addSurvey(survey).subscribe(newSurvey => null);
  }
}
