import {Component, OnInit, Input} from '@angular/core';
import {Event} from "../../../shared/";

@Component({
    selector: 'app-timeline',
    templateUrl: './timeline.component.html',
    styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {
    @Input()
    events: Event[];

    constructor() {
    }

    ngOnInit() {
        this.events = this.events.reverse();
    }

}
