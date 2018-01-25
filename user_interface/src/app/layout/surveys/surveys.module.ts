import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {FormsModule} from '@angular/forms';

import {SurveysRoutingModule} from './surveys-routing.module';
import {SurveysListComponent} from './surveys-list/surveys-list.component';
import {SurveyFormComponent} from './survey-form/survey-form.component';
import {SurveyEditFormComponent} from './survey-edit-form/survey-edit-form.component';


import {DndModule} from 'ng2-dnd';
import {SurveyDetailComponent} from './survey-detail/survey-detail.component';
import {TimelineComponent} from "./timeline/timeline.component";

@NgModule({
    imports: [
        CommonModule,
        SurveysRoutingModule,
        FormsModule,
        DndModule.forRoot(),
    ],
    declarations: [SurveysListComponent, SurveyFormComponent, SurveyEditFormComponent, SurveyDetailComponent, TimelineComponent]
})
export class SurveysModule {
}
