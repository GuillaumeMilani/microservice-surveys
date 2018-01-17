import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import {
    TimelineComponent,
} from './components';

@NgModule({
    imports: [
        CommonModule,
        NgbCarouselModule.forRoot(),
        DashboardRoutingModule,
    ],
    declarations: [
        DashboardComponent,
        TimelineComponent,
    ]
})
export class DashboardModule { }
