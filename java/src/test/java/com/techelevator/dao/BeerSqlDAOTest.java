package com.techelevator.dao;

import com.techelevator.Exceptions.BeerNotFoundException;
import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

public class BeerSqlDAOTest extends DAOIntegrationTest {

    private BeerSqlDAO beerSqlDAO;
    private Beer TEST_BEER;
    private final Long TEST_BREWERY_ID = 1L;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = -1L;

    @Before
    public void setup() throws BeerNotFoundException, BreweryNotFoundException {
        TEST_BEER = new Beer();
        TEST_BEER.setBeerName("TEST");
        TEST_BEER.setBeerStyle("TEST LAGER");
        TEST_BEER.setBeerDescription("TASTES LIKE A TEST");
        TEST_BEER.setBeerAbv(new BigDecimal("7.5"));
        TEST_BEER.setBreweryId(TEST_BREWERY_ID);
        TEST_BEER.setBeerImage("www.example.com");
        TEST_BEER.setActive(true);

        Brewery testBrewery = new Brewery();
        testBrewery.setBreweryName("TEST");
        testBrewery.setBrewerId(VALID_ID);
        testBrewery.setBreweryStreetAddress("1000 TEST AVE");
        testBrewery.setBreweryCity("TESTVILLE");
        testBrewery.setBreweryState("WEST TESTINGTON");
        testBrewery.setBreweryZipCode(12345);
        testBrewery.setBreweryWebsite("www.example.com");
        testBrewery.setActive(true);

        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        beerSqlDAO = new BeerSqlDAO(jdbcTemplate);
        BrewerySqlDAO brewerySqlDAO = new BrewerySqlDAO(jdbcTemplate);

        brewerySqlDAO.update(testBrewery, TEST_BREWERY_ID);
        beerSqlDAO.update(TEST_BEER, VALID_ID);
    }

    @Test
    public void createNewBeerTest_creates_a_new_beer() {
        int startingSize = beerSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        beerSqlDAO.create(TEST_BEER);

        int endingSize = beerSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        Assert.assertEquals( startingSize + 1, endingSize);
    }

    @Test(expected = Exception.class)
    public void createNewBeerTest_will_not_create_invalid_beer() {
        TEST_BEER.setBreweryId(INVALID_ID);

        beerSqlDAO.create(TEST_BEER);
    }

    @Test
    public void listTopRatedTest_returns_five_results() {
        List<Beer> actual = beerSqlDAO.listTopRated();

        Assert.assertEquals(5, actual.size());
    }

    @Test
    public void listByBreweryIdTest_returns_same_size_list_when_run_twice() {
        List<Beer> first = beerSqlDAO.listByBreweryId(TEST_BREWERY_ID);
        List<Beer> second = beerSqlDAO.listByBreweryId(TEST_BREWERY_ID);

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void findByIdTest_returns_proper_record() throws BeerNotFoundException {
        Beer actual = beerSqlDAO.findById(VALID_ID);

        Assert.assertEquals(VALID_ID, actual.getId());
    }

    @Test(expected = BeerNotFoundException.class)
    public void findByIdTest_will_not_return_invalid_record() throws BeerNotFoundException {
        beerSqlDAO.findById(INVALID_ID);
    }

    @Test
    public void updateBeerTest_updates_a_beer() throws BeerNotFoundException {
        String testName = "TEST TEST";
        Beer beerToUpdate = beerSqlDAO.findById(VALID_ID);
        beerToUpdate.setBeerName(testName);

        beerSqlDAO.update(beerToUpdate, beerToUpdate.getId());
        Beer updatedBeer = beerSqlDAO.findById(VALID_ID);

        Assert.assertEquals(testName, updatedBeer.getBeerName());
    }

    @Test(expected = BeerNotFoundException.class)
    public void deactivateBeerTest_deactivates_a_beer() throws BeerNotFoundException {
        beerSqlDAO.deactivate(VALID_ID);

        beerSqlDAO.findById(VALID_ID);
    }
}