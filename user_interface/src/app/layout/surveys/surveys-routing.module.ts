import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SurveysListComponent } from './surveys-list/surveys-list.component';

const routes: Routes = [
  { path: '', component: SurveysListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SurveysRoutingModule { }
