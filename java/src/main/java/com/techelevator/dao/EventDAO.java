package com.techelevator.dao;

import com.techelevator.Exceptions.EventNotFoundException;
import com.techelevator.model.Event;

import java.util.List;

public interface EventDAO {

    public void create(Event event);
    public List<Event> listUpcoming();
    public List<Event> listByBreweryId(Long breweryId);
    public Event findById(Long eventId) throws EventNotFoundException;
    public void update(Event event, Long eventId) throws EventNotFoundException;
    public void deactivate(Long eventId) throws EventNotFoundException;
}
