import { Component, OnInit, Input } from '@angular/core';

import { ExhaustiveSurvey } from '../../../shared';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { SurveyService } from '../../../shared';

import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';

@Component({
  selector: 'app-survey-detail',
  templateUrl: './survey-detail.component.html',
  styleUrls: ['./survey-detail.component.scss'],
  animations: [
    trigger('fadeInAnimation', [
      transition(':enter', [style({ transform: 'translateY(-100%)' }), animate('300ms ease-in')]),
    ])
  ],
    // attach the slide in/out animation to the host (root) element of this component
    host: { '[@fadeInAnimation]': '' }
})
export class SurveyDetailComponent implements OnInit {
  @Input('url') surveyUrl: string;
  survey: ExhaustiveSurvey;

  constructor(
    private route: ActivatedRoute,
    private surveyService: SurveyService,
    private location: Location,
  ) { }

  ngOnInit() {
    this.getSurvey();
  }

  getSurvey(): void {
    this.surveyService.getSurvey(this.surveyUrl).subscribe(survey => this.survey = survey);
  }
}
