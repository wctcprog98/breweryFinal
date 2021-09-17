package com.techelevator.dao;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.Exceptions.EventNotFoundException;
import com.techelevator.model.Brewery;
import com.techelevator.model.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventSqlDAOTest extends DAOIntegrationTest {

    private EventSqlDAO eventSqlDAO;
    private Event TEST_EVENT;
    private final Long TEST_BREWERY_ID = 1L;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = -1L;

    @Before
    public void setup() throws EventNotFoundException, BreweryNotFoundException {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        Date tomorrow = c.getTime();

        TEST_EVENT = new Event();
        TEST_EVENT.setEventName("TEST");
        TEST_EVENT.setEventDate(tomorrow);
        TEST_EVENT.setEventTime(new Time(14, 0, 0));
        TEST_EVENT.setEventDescription("COME TEST OUR TEST AT TEST");
        TEST_EVENT.setBreweryId(TEST_BREWERY_ID);
        TEST_EVENT.setActive(true);

        Brewery testBrewery = new Brewery();
        testBrewery.setBreweryName("TEST");
        testBrewery.setBrewerId(1L);
        testBrewery.setBreweryStreetAddress("1000 TEST AVE");
        testBrewery.setBreweryCity("TESTVILLE");
        testBrewery.setBreweryState("WEST TESTINGTON");
        testBrewery.setBreweryZipCode(12345);
        testBrewery.setBreweryWebsite("www.example.com");
        testBrewery.setActive(true);

        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        eventSqlDAO = new EventSqlDAO(jdbcTemplate);
        BrewerySqlDAO brewerySqlDAO = new BrewerySqlDAO(jdbcTemplate);

        brewerySqlDAO.update(testBrewery, TEST_BREWERY_ID);
        eventSqlDAO.update(TEST_EVENT, VALID_ID);
    }

    @Test
    public void createNewEventTest_creates_a_new_event() {
        int startingSize = eventSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        eventSqlDAO.create(TEST_EVENT);

        int endingSize = eventSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        Assert.assertEquals(startingSize + 1, endingSize);
    }

    @Test(expected = Exception.class)
    public void createNewEventTest_will_not_create_invalid_event() {
        TEST_EVENT.setBreweryId(INVALID_ID);

        eventSqlDAO.create(TEST_EVENT);
    }

    @Test
    public void listUpcomingTest_returns_five_results() {
        List<Event> actual = eventSqlDAO.listUpcoming();

        Assert.assertEquals(5, actual.size());
    }

    @Test
    public void listByBreweryIdTest_returns_same_size_list_when_run_twice() {
        List<Event> first = eventSqlDAO.listByBreweryId(TEST_BREWERY_ID);
        List<Event> second = eventSqlDAO.listByBreweryId(TEST_BREWERY_ID);

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void findByIdTest_returns_proper_record() throws EventNotFoundException {
        Event actual = eventSqlDAO.findById(VALID_ID);

        Assert.assertEquals(VALID_ID, actual.getId());
    }

    @Test(expected = EventNotFoundException.class)
    public void findByIdTest_will_not_return_invalid_record() throws EventNotFoundException {
        eventSqlDAO.findById(INVALID_ID);
    }

    @Test
    public void updateEventTest_updates_an_event() throws EventNotFoundException {
        String testName = "TEST TEST";
        Event eventToUpdate = eventSqlDAO.findById(VALID_ID);
        eventToUpdate.setEventName(testName);

        eventSqlDAO.update(eventToUpdate, eventToUpdate.getId());
        Event updatedEvent = eventSqlDAO.findById(VALID_ID);

        Assert.assertEquals(testName, updatedEvent.getEventName());
    }

    @Test(expected = EventNotFoundException.class)
    public void deactivateEventTest_deactivates_an_event() throws EventNotFoundException {
        eventSqlDAO.deactivate(VALID_ID);

        eventSqlDAO.findById(VALID_ID);
    }
}