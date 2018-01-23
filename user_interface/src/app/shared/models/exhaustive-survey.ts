import { Link } from './link';
import {NewSurvey} from "./new-survey";

export class ExhaustiveSurvey extends NewSurvey {
    datetime: string;
    status: SurveyStatus;
    links: Link[];
};

export enum SurveyStatus {
    draft,
    opened,
    closed,
};
