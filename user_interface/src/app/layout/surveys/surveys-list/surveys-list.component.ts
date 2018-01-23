import { Component, OnInit } from '@angular/core';
import { ExhaustiveSurvey, SurveyService, SessionService } from '../../../shared';

@Component({
  selector: 'app-surveys-list',
  templateUrl: './surveys-list.component.html',
  styleUrls: ['./surveys-list.component.scss']
})
export class SurveysListComponent implements OnInit {
  surveys: ExhaustiveSurvey[];

  constructor(
    private surveyService: SurveyService, 
    private sessionService: SessionService
  ) { }

  ngOnInit() {
    this.getSurveys();
  }

  getSurveys(): void {
    this.surveyService.getSurveys().subscribe(surveys => this.surveys = surveys);
  }

  setDetailUrl(url: string): void {
    this.sessionService.setDetailUrl(url);
  }
}
