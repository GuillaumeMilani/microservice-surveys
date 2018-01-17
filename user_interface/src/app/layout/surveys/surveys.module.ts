import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SurveysRoutingModule } from './surveys-routing.module';
import { SurveysListComponent } from './surveys-list/surveys-list.component';

@NgModule({
  imports: [
    CommonModule,
    SurveysRoutingModule
  ],
  declarations: [SurveysListComponent]
})
export class SurveysModule { }
