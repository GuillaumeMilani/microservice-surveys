import { Injectable } from '@angular/core';
import { ExhaustiveSurvey } from '../../models';
import { SURVEYS } from './mock-surveys';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessagesService } from '../messages/messages.service';
import { MessageType } from '../../models/message';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class SurveyService {
	private surveysURL = 'http://localhost:8080/api/surveys';  // URL to web api
  constructor(
  private http: HttpClient,
  private messageService: MessagesService) { }

  getSurveys(): Observable<ExhaustiveSurvey[]> {
	return this.http.get<ExhaustiveSurvey[]>(this.surveysURL)
    .pipe(
	  tap(surveys => this.log(`fetched surveys`)),
      catchError(this.handleError('getSurveys', []))
    );
  }

  getSurvey(url: string): Observable<ExhaustiveSurvey> {
    // TODO : Use the link to GET
  return this.http.get<ExhaustiveSurvey>(url).pipe(
    tap(_ => this.log(`fetched survey`)),
    catchError(this.handleError<ExhaustiveSurvey>(`getSurvey url=${url}`))
  );
  }
  
  /** Log a SurveyService message with the MessageService */
	private log(message: string) {
	  this.messageService.addMessage({"id":1, "type":MessageType.info,"message":message});
	}
	
	/**
	 * Handle Http operation that failed.
	 * Let the app continue.
	 * @param operation - name of the operation that failed
	 * @param result - optional value to return as the observable result
	 */
	private handleError<T> (operation = 'operation', result?: T) {
	  return (error: any): Observable<T> => {

		// TODO: send the error to remote logging infrastructure
		console.error(error); // log to console instead

		// TODO: better job of transforming error for user consumption
		this.log(`${operation} failed: ${error.message}`);

		// Let the app keep running by returning an empty result.
		return of(result as T);
	  };
	}
}
