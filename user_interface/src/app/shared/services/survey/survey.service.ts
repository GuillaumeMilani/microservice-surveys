import { Injectable } from '@angular/core';
import { ExhaustiveSurvey } from '../../models';
import { SURVEYS } from './mock-surveys';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class SurveyService {
  constructor() { }

  getSurveys(): Observable<ExhaustiveSurvey[]> {
    return of(SURVEYS);
  }

  getSurvey(url: string): Observable<ExhaustiveSurvey> {
    // TODO : Use the link to GET
    return of(SURVEYS[0]);
  }
}
