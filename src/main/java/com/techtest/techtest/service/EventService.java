package com.techtest.techtest.service;


import com.techtest.techtest.model.Event;
import com.techtest.techtest.model.EventPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    private final String eventsUrl;
    private final RestTemplate restTemplate;
    
    public EventService(@Value("${events.url}") String eventsUrl,
                        @Autowired RestTemplate restTemplate) {
        this.eventsUrl = eventsUrl;
        this.restTemplate = restTemplate;
    }

    public List<Event> eventsSortedByDate(String sort) {
        ResponseEntity<EventPage> eventPage = restTemplate.getForEntity(eventsUrl,  EventPage.class);
        List<Event> eventList = Objects.requireNonNull(eventPage.getBody()).getEvents();

        return (sort != null && sort.equals("DESC"))
                ? sortDescending(eventList)
                : sortAscending(eventList);
    }

    private List<Event> sortAscending(List<Event> events) {
        Collections.sort(events, Comparator.comparing(Event::getStartDate));
        return events;
    }

    private List<Event> sortDescending(List<Event> events) {
        Collections.sort(events, Comparator.comparing(Event::getStartDate).reversed());
        return events;
    }
}
