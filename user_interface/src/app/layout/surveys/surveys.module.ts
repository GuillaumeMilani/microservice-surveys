import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SurveysRoutingModule } from './surveys-routing.module';
import { SurveysListComponent } from './surveys-list/surveys-list.component';
import { SurveyDetailComponent } from './survey-detail/survey-detail.component';

@NgModule({
  imports: [
    CommonModule,
    SurveysRoutingModule,
  ],
  declarations: [SurveysListComponent, SurveyDetailComponent]
})
export class SurveysModule { }
