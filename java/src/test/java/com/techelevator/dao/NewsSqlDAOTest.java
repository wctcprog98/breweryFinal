package com.techelevator.dao;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.Exceptions.NewsNotFoundException;
import com.techelevator.model.Brewery;
import com.techelevator.model.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

public class NewsSqlDAOTest extends DAOIntegrationTest {

    private NewsSqlDAO newsSqlDAO;
    private News TEST_NEWS;
    private final Long TEST_BREWERY_ID = 1L;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = -1L;

    @Before
    public void setup() throws NewsNotFoundException, BreweryNotFoundException {
        TEST_NEWS = new News();
        TEST_NEWS.setNewsName("TEST");
        TEST_NEWS.setNewsBody("TEST TODAY AT TEST NEWS TEST");
        TEST_NEWS.setBreweryId(TEST_BREWERY_ID);
        TEST_NEWS.setActive(true);

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
        newsSqlDAO = new NewsSqlDAO(jdbcTemplate);
        BrewerySqlDAO brewerySqlDAO = new BrewerySqlDAO(jdbcTemplate);

        brewerySqlDAO.update(testBrewery, TEST_BREWERY_ID);
        newsSqlDAO.update(TEST_NEWS, VALID_ID);
    }

    @Test
    public void createNewsTest_creates_a_news_post() {
        int startingSize = newsSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        newsSqlDAO.create(TEST_NEWS);

        int endingSize = newsSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        Assert.assertEquals(startingSize + 1, endingSize);
    }

    @Test(expected = Exception.class)
    public void createNewsTest_will_not_create_invalid_news_post() {
        TEST_NEWS.setBreweryId(INVALID_ID);

        newsSqlDAO.create(TEST_NEWS);
    }

    @Test
    public void listByBreweryIdTest_returns_same_size_list_when_run_twice() {
        List<News> first = newsSqlDAO.listByBreweryId(TEST_BREWERY_ID);
        List<News> second = newsSqlDAO.listByBreweryId(TEST_BREWERY_ID);

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void findById_returns_proper_record() throws NewsNotFoundException {
        News actual = newsSqlDAO.findById(VALID_ID);

        Assert.assertEquals(VALID_ID, actual.getId());
    }

    @Test(expected = NewsNotFoundException.class)
    public void findById_will_not_return_invalid_record() throws NewsNotFoundException {
        newsSqlDAO.findById(INVALID_ID);
    }

    @Test
    public void updateNewsTest_updates_a_news_post() throws NewsNotFoundException {
        String testName = "TEST TEST";
        News newsToUpdate = newsSqlDAO.findById(VALID_ID);
        newsToUpdate.setNewsName(testName);

        newsSqlDAO.update(newsToUpdate, newsToUpdate.getId());
        News updatedNews = newsSqlDAO.findById(VALID_ID);

        Assert.assertEquals(testName, updatedNews.getNewsName());
    }

    @Test(expected = NewsNotFoundException.class)
    public void deactivateNewsTest_deactivates_a_news_post() throws NewsNotFoundException {
        newsSqlDAO.deactivate(VALID_ID);

        newsSqlDAO.findById(VALID_ID);
    }
}