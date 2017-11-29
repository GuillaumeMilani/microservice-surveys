package io.lozzikit.survey.services;

import io.lozzikit.survey.api.exceptions.NotFoundException;
import io.lozzikit.survey.api.model.Event;
import io.lozzikit.survey.entities.EventEntity;
import io.lozzikit.survey.repositories.EventRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public String saveEvent(Event event) {
        event.setDatetime(DateTime.now());

        EventEntity eventEntity = DTOToEntity(event);

        eventRepository.save(eventEntity);
        return eventEntity.getId();
    }

    public Event getEvent(String id) throws NotFoundException {
        EventEntity eventEntity = eventRepository.findOne(id);

        if (null == eventEntity) {
            throw new NotFoundException(404, "Event not found");
        }

        return entityToDTO(eventEntity);
    }

    private EventEntity DTOToEntity(Event event) {
        EventEntity eventEntity = new EventEntity();

        eventEntity.setStatus(event.getStatus());
        eventEntity.setDateTime(event.getDatetime());
        eventEntity.setSurveyId(event.getSurveyId());

        return eventEntity;
    }

    private Event entityToDTO(EventEntity eventEntity) {
        Event event = new Event();

        event.setStatus(eventEntity.getStatus());
        event.setDatetime(eventEntity.getDateTime());
        event.setSurveyId(eventEntity.getSurveyId());

        return event;
    }
}
