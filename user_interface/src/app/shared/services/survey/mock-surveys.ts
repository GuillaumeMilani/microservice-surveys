import { ExhaustiveSurvey, SurveyStatus } from '../../models';

export const SURVEYS: ExhaustiveSurvey[] = [
    {
        title: "The survey", user: { username: "The user" }, datetime: "2018-01-17",
        description: "This is a mock survey", status: SurveyStatus.opened,
        questions: [{ number: 0, question: "How are you ?" }, { number: 1, question: "What's your name ?" }],
        links: [
            { rel: "", href: "http://www.google.ch" }
        ]
    },
    {
        title: "Yet another survey", user: { username: "Another user" }, datetime: "2018-01-17",
        description: "Also a mock survey", status: SurveyStatus.opened,
        questions: [{ number: 0, question: "You still good ?" }, { number: 1, question: "And what's your mother's name ?" }],
        links: [
            { rel: "", href: "http://www.facebook.com" }
        ]
    }
];