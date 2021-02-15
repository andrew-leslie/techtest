package com.techtest.techtest.controller;

import com.techtest.techtest.model.Event;
import com.techtest.techtest.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController("/")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents(@PathParam("sort") String sort) {
        return eventService.eventsSortedByDate(sort);
    }
}
