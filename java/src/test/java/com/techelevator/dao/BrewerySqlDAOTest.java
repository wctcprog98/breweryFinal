package com.techelevator.dao;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.model.Brewery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;

public class BrewerySqlDAOTest extends DAOIntegrationTest {

    private BrewerySqlDAO brewerySqlDAO;
    private Brewery TEST_BREWERY;
    private final Long TEST_BREWER_ID = 1L;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = -1L;

    @Before
    public void setup() throws BreweryNotFoundException {
        TEST_BREWERY = new Brewery();
        TEST_BREWERY.setBreweryName("TEST");
        TEST_BREWERY.setBrewerId(TEST_BREWER_ID);
        TEST_BREWERY.setBreweryStreetAddress("1000 TEST AVE");
        TEST_BREWERY.setBreweryCity("TESTVILLE");
        TEST_BREWERY.setBreweryState("WEST TESTINGTON");
        TEST_BREWERY.setBreweryZipCode(12345);
        TEST_BREWERY.setBreweryWebsite("www.example.com");
        TEST_BREWERY.setActive(true);

        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        brewerySqlDAO = new BrewerySqlDAO(jdbcTemplate);

        brewerySqlDAO.update(TEST_BREWERY, VALID_ID);
    }

    @Test
    public void createNewBreweryTest_creates_a_new_brewery() {
        int startingSize = brewerySqlDAO.listAll().size();
        brewerySqlDAO.create(TEST_BREWERY);

        int endingSize = brewerySqlDAO.listAll().size();
        Assert.assertEquals(startingSize + 1, endingSize);
    }

    @Test(expected = Exception.class)
    public void createNewBreweryTest_will_not_create_invalid_brewery() {
        TEST_BREWERY.setBrewerId(INVALID_ID);

        brewerySqlDAO.create(TEST_BREWERY);
    }

    @Test
    public void listAllTest_returns_same_size_list_when_run_twice() {
        brewerySqlDAO.create(TEST_BREWERY);

        List<Brewery> first = brewerySqlDAO.listAll();
        List<Brewery> second = brewerySqlDAO.listAll();

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void findByIDTest_returns_the_proper_record() throws BreweryNotFoundException {
        Brewery actual = brewerySqlDAO.findById(VALID_ID);

        Assert.assertEquals(VALID_ID, actual.getId());
    }

    @Test(expected = BreweryNotFoundException.class)
    public void findByIDTest_will_not_return_invalid_record() throws BreweryNotFoundException {
        Brewery invalidBrewery = brewerySqlDAO.findById(INVALID_ID);
    }

    @Test
    public void updateBreweryTest_updates_a_brewery() throws BreweryNotFoundException {
        String testName = "TEST NAME";
        Brewery breweryToUpdate = brewerySqlDAO.findById(VALID_ID);
        breweryToUpdate.setBreweryName(testName);

        brewerySqlDAO.update(breweryToUpdate, breweryToUpdate.getId());
        Brewery updatedBrewery = brewerySqlDAO.findById(VALID_ID);

        Assert.assertEquals(testName, updatedBrewery.getBreweryName());
    }

    @Test(expected = BreweryNotFoundException.class)
    public void deactivateBreweryTest_deactivates_brewery() throws BreweryNotFoundException {
        brewerySqlDAO.deactivate(VALID_ID);

        brewerySqlDAO.findById(VALID_ID);
    }
}