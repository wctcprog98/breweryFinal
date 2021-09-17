package com.techelevator.controller;

import com.techelevator.Exceptions.EventNotFoundException;
import com.techelevator.dao.EventSqlDAO;
import com.techelevator.model.Event;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    @Autowired
    EventSqlDAO eventSqlDAO;

    @ApiOperation("Create a new Event")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public void create(@ApiParam(value = "Event Object") @Valid @RequestBody Event event) {
        eventSqlDAO.create(event);
    }

    @ApiOperation("List 5 most-upcoming Events, system-wide")
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> listUpcoming() {
        return eventSqlDAO.listUpcoming();
    }

    @ApiOperation("List all Events by Brewery ID")
    @RequestMapping(path = "/breweries/{id}/events", method = RequestMethod.GET)
    public List<Event> listByBreweryId(@ApiParam(value = "Brewery ID") @PathVariable Long id) {
        return eventSqlDAO.listByBreweryId(id);
    }

    @ApiOperation("Return an Event by its ID")
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public Event findById(@ApiParam(value = "Event ID") @PathVariable Long id) throws EventNotFoundException {
        return eventSqlDAO.findById(id);
    }

    @ApiOperation("Update an Event")
    @RequestMapping(path = "/events/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(value = "Event Object") @Valid @RequestBody Event event,
                       @ApiParam(value = "Event ID") @PathVariable Long id) throws EventNotFoundException {
        eventSqlDAO.update(event, id);
    }

    @ApiOperation("Deactivate an Event")
    @RequestMapping(path = "/events/{id}", method = RequestMethod.DELETE)
    public void deactivate(@ApiParam(value = "Event ID") @PathVariable Long id) throws EventNotFoundException {
        try {
            eventSqlDAO.deactivate(id);
        } catch (EventNotFoundException e) {
            throw new EventNotFoundException();
        }
    }
}
