package com.yaphet.devhackathonabitpain.services;

import com.yaphet.devhackathonabitpain.models.Division;
import com.yaphet.devhackathonabitpain.models.Event;
import com.yaphet.devhackathonabitpain.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAllUnDeleted();
    }

    public Event getEvent(Long id) {
        Event event =eventRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Division not found with id=%d",id)));
        return event;
    }

    public void create(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void update(Event event) {
        //TODO: check if there is a real update
        Long id=event.getId();
        Event tempEvent=eventRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Division not found with id=%d",id)));
        eventRepository.save(event);
    }

    public void delete(Long id) {
        Event tempEvent=eventRepository.findById(id).orElseThrow(()->new IllegalStateException(String.format("Division not found with id=%d",id)));
        eventRepository.deleteById(id);
    }
}
