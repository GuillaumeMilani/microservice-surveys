import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SurveysListComponent } from './surveys-list/surveys-list.component';
import { SurveyDetailComponent } from './survey-detail/survey-detail.component';

const routes: Routes = [
  { path: '', component: SurveysListComponent }
  { path: 'detail', component: SurveyDetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
