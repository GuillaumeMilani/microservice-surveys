package io.lozzikit.survey.api.spec.steps.events;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.lozzikit.survey.ApiException;
import io.lozzikit.survey.api.dto.Event;
import io.lozzikit.survey.api.spec.helpers.Environment;
import io.lozzikit.survey.api.spec.steps.surveys.SurveySteps;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tony on 17.11.2017.
 */
public class GetStatusSteps extends SurveySteps {
    private String Id;

    public GetStatusSteps(Environment environment) {
        super(environment);
    }


    @When("^I GET its events from the status endpoint$")
    public void iGETItFromTheSurveyIDEndpoint() throws Throwable {
        try {
            lastApiResponse = api.getSurveyEventsWithHttpInfo(environment.getLastId());
            lastApiCallThrewException = false;
            lastApiException = null;
            environment.setLastStatusCode(lastApiResponse.getStatusCode());
            environment.setEvents((List<Event>) lastApiResponse.getData());
        } catch (ApiException e) {
            lastApiCallThrewException = true;
            lastApiResponse = null;
            lastApiException = e;
            environment.setLastStatusCode(lastApiException.getCode());
        }
    }

    @And("^I receive no event")
    public void iReceiveNoEvent() throws Throwable {
        assertEquals(0, environment.getEvents().size());
    }

    @And("^I receive (\\d) events")
    public void iReceiveEvents(long numberOfEvents) {
        List<Event> events = environment.getEvents();

        assertEquals(numberOfEvents, events.size());
    }

    @And("^I receive (\\d) (DRAFT|OPENED|CLOSED|) event")
    public void iReceiveSomeEvents(long numberOfEvents, String eventType) throws Throwable {
        List<Event> events = environment.getEvents();

        long countEvents = events.stream().filter(event -> event.getStatus().equals(io.lozzikit.survey.api.dto.Status.valueOf(eventType))).count();
        assertEquals(numberOfEvents, countEvents);
    }
}
