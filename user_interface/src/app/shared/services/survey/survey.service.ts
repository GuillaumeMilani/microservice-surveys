import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {MessagesService} from '../messages/messages.service';
import {MessageType, NewSurvey, ExhaustiveSurvey, Event} from '../../models/';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, map, tap} from 'rxjs/operators';

import {environment} from '../../../../environments/environment';
import {NewEvent} from "../../models/new-event";
import {Message} from "../../models/message";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
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
                tap(surveys => console.log(`fetched surveys`)),
                catchError(this.handleError('getSurveys', []))
            );
    }

    getSurvey(url: string): Observable<ExhaustiveSurvey> {
        return this.http.get<ExhaustiveSurvey>(url).pipe(
            tap(_ => console.log(`fetched survey`)),
            catchError(this.handleError<ExhaustiveSurvey>(`getSurvey url=${url}`))
        );
    }

    /** PATCH: change a survey status */
    updateSurveyStatus(survey: ExhaustiveSurvey): Observable<any> {
        const postUrl = `${survey.links.find(link => link.rel === "events").href}`;
        const newEvent: NewEvent = new NewEvent();
        newEvent.status = survey.status;

        return this.http.post(postUrl, newEvent, httpOptions).pipe(
            tap(_ => console.log(`updated survey url=${postUrl}`)),
            catchError(this.handleError<any>('updateSurveyStatus'))
        );
    }

    /** POST: add a new Survey to the server */
    addSurvey(survey: NewSurvey): Observable<NewSurvey> {
        return this.http.post<NewSurvey>(this.surveysURL, survey, httpOptions).pipe(
            tap(_ => console.log(`added survey`)),
            catchError(this.handleError<NewSurvey>('addSurvey'))
        );
    }

    /** DELETE: delete a survey from the server */
    deleteSurvey(survey: ExhaustiveSurvey): Observable<any> {
        const deleteUrl = survey.links.find(link => link.rel === "self").href;
        return this.http.delete(deleteUrl).pipe(
            tap(_ => console.log("deleted survey")),
            catchError(this.handleError<any>("deleteSurvey"))
        );
    }

    /** GET: get the events of a survey */
    getEvents(survey: ExhaustiveSurvey): Observable<Event[]> {
        const getUrl = `${survey.links.find(link => link.rel === "events").href}`;
        return this.http.get<Event[]>(getUrl)
            .pipe(
                tap(surveys => console.log(`fetched events`)),
                catchError(this.handleError('getEvents', []))
            );
    }

    /** Log a SurveyService message with the MessageService */
    private logError(text: string) {
        let message: Message = new Message();
        message.message = text;
        message.type = MessageType.error;
        this.messageService.addMessage(message);
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            console.error(error);
            this.logError(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}
