import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule }   from '@angular/forms';

import { SurveysRoutingModule } from './surveys-routing.module';
import { SurveysListComponent } from './surveys-list/surveys-list.component';
import { SurveyDetailComponent } from './survey-detail/survey-detail.component';

import { DndModule } from 'ng2-dnd';

@NgModule({
  imports: [
    CommonModule,
    SurveysRoutingModule,
    FormsModule,
    DndModule.forRoot()
  ],
  declarations: [SurveysListComponent, SurveyDetailComponent]
})
export class SurveysModule { }
