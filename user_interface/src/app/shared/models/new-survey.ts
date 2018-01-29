import { User } from './user';
import { Question } from './question';
import { Link } from './link';

export class NewSurvey {
    user: User = new User();
    title: string;
    description: string;
    questions: Question[] = [];
};
