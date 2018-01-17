import { ExhaustiveSurvey, SurveyStatus } from '../../models';

export const SURVEYS: ExhaustiveSurvey[] = [
    {
        title: "The survey", user: { username: "The user" }, datetime: "2018-01-17",
        description: "This is a mock survey", status: SurveyStatus.opened,
        questions: [{ number: 0, question: "How are you ?" }, { number: 1, question: "What's your name ?" }],
        links: []
    }
];