package com.techelevator.dao;

import com.techelevator.Exceptions.EventNotFoundException;
import com.techelevator.model.Event;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class EventSqlDAO implements EventDAO {

    private JdbcTemplate jdbcTemplate;

    public EventSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Event event) {
        String sql = "INSERT INTO events (event_name, event_date, event_time, event_description, brewery_id) " +
                     "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, event.getEventName(), event.getEventDate(), event.getEventTime(),
                                 event.getEventDescription(), event.getBreweryId());
    }

    @Override
    public List<Event> listUpcoming(){
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_date, event_time, event_description, brewery_id, active " +
                     "FROM events WHERE active = true AND event_date >= CURRENT_DATE ORDER BY event_date LIMIT 5";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Event event = mapRowToEvent(results);
            events.add(event);
        }

        return events;
    }

    @Override
    public List<Event> listByBreweryId(Long breweryId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_date, event_time, event_description, brewery_id, active " +
                     "FROM events WHERE brewery_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);

        while (results.next()) {
            Event event = mapRowToEvent(results);
            events.add(event);
        }

        return events;
    }

    @Override
    public Event findById(Long eventId) throws EventNotFoundException {
        String sql = "SELECT event_id, event_name, event_date, event_time, event_description, brewery_id, active " +
                     "FROM events WHERE event_id = ? AND active = true";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, eventId);

        if(result.next()) {
            return mapRowToEvent(result);
        }

        throw new EventNotFoundException();
    }

    @Override
    public void update(Event event, Long eventId) throws EventNotFoundException {
        String sql = "UPDATE events SET event_name = ?, event_date = ?, event_time = ?, event_description = ?, " +
                            "brewery_id = ?, active = ? WHERE event_id = ?";

        try {
            jdbcTemplate.update(sql, event.getEventName(), event.getEventDate(), event.getEventTime(),
                                     event.getEventDescription(), event.getBreweryId(), event.isActive(), eventId);
        } catch (DataAccessException e) {
            throw new EventNotFoundException();
        }
    }

    @Override
    public void deactivate(Long eventId) throws EventNotFoundException {
        String sql = "UPDATE events SET active = false WHERE event_id = ?";

        try {
            jdbcTemplate.update(sql, eventId);
        } catch (DataAccessException e) {
            throw new EventNotFoundException();
        }
    }

    public Event mapRowToEvent(SqlRowSet results) {
        Event event = new Event();

        event.setId(results.getLong("event_id"));
        event.setEventName(results.getString("event_name"));
        event.setEventDate(results.getDate("event_date"));
        event.setEventTime(results.getTime("event_time"));
        event.setEventDescription(results.getString("event_description"));
        event.setBreweryId(results.getLong("brewery_id"));
        event.setActive(results.getBoolean("active"));

        return event;
    }
}
