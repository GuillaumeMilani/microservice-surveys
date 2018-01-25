import {Component, OnInit, Input} from '@angular/core';

import {Location} from '@angular/common';

import {SurveyService, SessionService} from '../../../shared';
import {SurveyFormComponent} from "../survey-form/survey-form.component";
import {ExhaustiveSurvey} from "../../../shared/models/exhaustive-survey";

@Component({
    selector: 'app-survey-edit-form',
    templateUrl: '../survey-form/survey-form.component.html',
    styleUrls: ['../survey-form/survey-form.component.scss']
})
export class SurveyEditFormComponent extends SurveyFormComponent {
    constructor(protected surveyService: SurveyService,
                protected location: Location,
                private sessionService: SessionService) {
        super(surveyService, location);
    }

    ngOnInit() {
        this.getSurvey();
    }

    getSurvey(): void {
        if (this.sessionService.getDetailUrl()) {
            this.surveyService.getSurvey(this.sessionService.getDetailUrl()).subscribe(survey => this.survey = survey);
        } else {
        }
    }

    save(survey: ExhaustiveSurvey): void {
        // TODO
    }
}
