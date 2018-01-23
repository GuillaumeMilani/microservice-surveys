import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SurveysListComponent } from './surveys-list/surveys-list.component';
import { SurveyFormComponent } from './survey-form/survey-form.component';
import {SurveyEditFormComponent} from "./survey-edit-form/survey-edit-form.component";

const routes: Routes = [
  { path: '', component: SurveysListComponent },
  { path: 'detail', component: SurveyEditFormComponent },
  { path: 'create', component: SurveyFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
