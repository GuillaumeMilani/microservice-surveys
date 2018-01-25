import {SurveyStatus} from "./exhaustive-survey";
import {NewEvent} from "./new-event";

export class Event extends NewEvent {
    datetime: string;
    surveyId: string;
}
