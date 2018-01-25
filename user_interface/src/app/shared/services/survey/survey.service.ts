import { Injectable } from '@angular/core';
import { ExhaustiveSurvey } from '../../models';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessagesService } from '../messages/messages.service';
import { MessageType } from '../../models/message';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import {NewSurvey} from "../../models/new-survey";

import { environment } from '../../../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class SurveyService {
    private surveysURL = `http://${environment.serverHost}:${environment.serverPort}/api/surveys`;  // URL to web api

    constructor(private http: HttpClient,
                private messageService: MessagesService) {
    }

    getSurveys(): Observable<ExhaustiveSurvey[]> {
        return this.http.get<ExhaustiveSurvey[]>(this.surveysURL)
            .pipe(
                tap(surveys => this.log(`fetched surveys`)),
                catchError(this.handleError('getSurveys', []))
            );
    }

    getSurvey(url: string): Observable<ExhaustiveSurvey> {
        return this.http.get<ExhaustiveSurvey>(url).pipe(
            tap(_ => this.log(`fetched survey`)),
            catchError(this.handleError<ExhaustiveSurvey>(`getSurvey url=${url}`))
        );
    }

    /** PATCH: change a survey status */
    updateSurveyStatus(survey: ExhaustiveSurvey): Observable<any> {
        const patchUrl = `${survey.links.find(link => link.rel === "events").href}`;
        const body = `"${survey.status.toString()}"`;
        return this.http.patch(patchUrl, body, httpOptions).pipe(
            tap(_ => this.log(`updated survey url=${patchUrl}`)),
            catchError(this.handleError<any>('updateSurveyStatus'))
        );
    }

    /** POST: add a new Survey to the server */
    addSurvey(survey: NewSurvey): Observable<NewSurvey> {
        return this.http.post<NewSurvey>(this.surveysURL, survey, httpOptions).pipe(
            tap(_ => this.log(`added survey`)),
            catchError(this.handleError<NewSurvey>('addSurvey'))
        );
    }

    /** GET: get the events of a survey */
    getEvents(survey: ExhaustiveSurvey): Observable<Event[]> {
        const getUrl = `${survey.links.find(link => link.rel === "events").href}`;
        return this.http.get<Event[]>(getUrl)
            .pipe(
                tap(surveys => this.log(`fetched events`)),
                catchError(this.handleError('getEvents', []))
            );
    }

    /** Log a SurveyService message with the MessageService */
    private log(message: string) {
        this.messageService.addMessage({"id": 1, "type": MessageType.info, "message": message});
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
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
