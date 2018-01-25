import {Component, OnInit, Input} from '@angular/core';
import {Event} from "../../../shared/";

@Component({
    selector: 'app-timeline',
    templateUrl: './timeline.component.html',
    styleUrls: ['./timeline.component.scss']
})
export class TimelineComponent implements OnInit {
    events: Event[] = [];

    constructor() {
    }

    @Input()
    setEvents(events: Event[]): void {
        this.events = events;
    }

    ngOnInit() {
    }

}
