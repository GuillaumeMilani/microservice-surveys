import { User } from './user';
import { Question } from './question';
import { Link } from './link';

export class ExhaustiveSurvey {
    user: User;
    title: string;
    description: string;
    questions: Question[];
    datetime: string;
    status: SurveyStatus;
    links: Link[];
};

export enum SurveyStatus {
    draft,
    opened,
    closed,
};