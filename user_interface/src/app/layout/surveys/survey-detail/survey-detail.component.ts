import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';

import {SurveyService} from "../../../shared/services/survey/survey.service";
import {NewSurvey, Event} from "../../../shared/models";

@Component({
    selector: 'app-survey-detail',
    templateUrl: './survey-detail.component.html',
    styleUrls: ['./survey-detail.component.scss']
})
export class SurveyDetailComponent implements OnInit {
    survey: NewSurvey = new NewSurvey();
    events: Event[];

    constructor(private surveyService: SurveyService,
                private location: Location) {
    }

    ngOnInit() {
        let url = localStorage.getItem("surveyDetailUrl");
        if (url) {
            this.surveyService.getSurvey(url).subscribe(survey => {
                this.survey = survey;
                this.surveyService.getEvents(survey).subscribe(events => this.events = events);
            });
        } else {
        }
    }

    goBack(): void {
        localStorage.removeItem("surveyDetailUrl");
        this.location.back();
    }
}
