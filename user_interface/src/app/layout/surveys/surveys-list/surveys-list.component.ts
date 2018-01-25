import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ExhaustiveSurvey, SurveyService} from '../../../shared';
import {SurveyStatus} from "../../../shared/models/exhaustive-survey";

@Component({
    selector: 'app-surveys-list',
    templateUrl: './surveys-list.component.html',
    styleUrls: ['./surveys-list.component.scss']
})
export class SurveysListComponent implements OnInit {
    surveys: ExhaustiveSurvey[];

    constructor(private surveyService: SurveyService,
                private router: Router,
                private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.getSurveys();
    }

    getSurveys(): void {
        this.surveyService.getSurveys().subscribe(surveys => this.surveys = surveys);
    }

    setDetailUrl(url: string): void {
        localStorage.setItem("surveyDetailUrl", url);
        // this.sessionService.setDetailUrl(url);
    }

    openSurvey(survey: ExhaustiveSurvey): void {
        survey.status = SurveyStatus.opened;
        this.surveyService.updateSurveyStatus(survey).subscribe(_ => _);
    }

    closeSurvey(survey: ExhaustiveSurvey): void {
        survey.status = SurveyStatus.closed;
        this.surveyService.updateSurveyStatus(survey).subscribe(_ => _);
    }

    showDetails(survey: ExhaustiveSurvey): void {
        this.setDetailUrl(survey.links.find(link => link.rel == 'self').href);
        this.router.navigate(['/detail'], {relativeTo: this.activatedRoute});
    }
}
