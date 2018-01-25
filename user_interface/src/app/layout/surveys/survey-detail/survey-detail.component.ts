import {Component, OnInit} from '@angular/core';
import {SessionService} from "../../../shared/services/session/session.service";
import {SurveyService} from "../../../shared/services/survey/survey.service";
import {NewSurvey} from "../../../shared/models/new-survey";
import {Event} from "../../../shared/models/";

@Component({
    selector: 'app-survey-detail',
    templateUrl: './survey-detail.component.html',
    styleUrls: ['./survey-detail.component.scss']
})
export class SurveyDetailComponent implements OnInit {
    survey: NewSurvey = new NewSurvey();
    events: Event[] = [];

    constructor(private sessionService: SessionService,
                private surveyService: SurveyService) {
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
}
