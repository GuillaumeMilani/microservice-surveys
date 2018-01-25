import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SurveysListComponent } from './surveys-list/surveys-list.component';
import { SurveyFormComponent } from './survey-form/survey-form.component';
import {SurveyDetailComponent} from "./survey-detail/survey-detail.component";

const routes: Routes = [
  { path: '', component: SurveysListComponent },
  { path: 'detail', component: SurveyDetailComponent },
  { path: 'create', component: SurveyFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
