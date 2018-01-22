import { Component, OnInit } from '@angular/core';
import { ExhaustiveSurvey, SurveyService } from '../../../shared';

@Component({
  selector: 'app-surveys-list',
  templateUrl: './surveys-list.component.html',
  styleUrls: ['./surveys-list.component.scss']
})
export class SurveysListComponent implements OnInit {
  surveys: ExhaustiveSurvey[];
  currentShowDetail: number;

  constructor(private surveyService: SurveyService) { }

  ngOnInit() {
    this.getSurveys();
  }

  getSurveys(): void {
    this.surveyService.getSurveys().subscribe(surveys => this.surveys = surveys);
  }

  toggleDetail(index: number):void {
    this.isShowDetail(index) ? this.currentShowDetail = null : this.currentShowDetail = index;
  }

  isShowDetail(index: number): boolean {
    return index === this.currentShowDetail;
  }
}
