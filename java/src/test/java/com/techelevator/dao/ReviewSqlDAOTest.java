package com.techelevator.dao;

import com.techelevator.Exceptions.BeerNotFoundException;
import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.Exceptions.ReviewNotFoundException;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import com.techelevator.model.Review;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

public class ReviewSqlDAOTest extends DAOIntegrationTest {

    private ReviewSqlDAO reviewSqlDAO;
    private Review TEST_REVIEW;
    private final Long TEST_BREWERY_ID = 1L;
    private final Long TEST_BEER_ID = 1L;
    private final Long VALID_ID = 1L;
    private final Long INVALID_ID = -1L;

    @Before
    public void setup() throws ReviewNotFoundException, BeerNotFoundException, BreweryNotFoundException {
        TEST_REVIEW = new Review();
        TEST_REVIEW.setBeerId(TEST_BEER_ID);
        TEST_REVIEW.setReviewerId(VALID_ID);
        TEST_REVIEW.setReviewText("TASTED JUST LIKE A TEST");
        TEST_REVIEW.setStarRating(new BigDecimal("4.5"));
        TEST_REVIEW.setActive(true);

        Brewery testBrewery = new Brewery();
        testBrewery.setBreweryName("TEST");
        testBrewery.setBrewerId(1L);
        testBrewery.setBreweryStreetAddress("1000 TEST AVE");
        testBrewery.setBreweryCity("TESTVILLE");
        testBrewery.setBreweryState("WEST TESTINGTON");
        testBrewery.setBreweryZipCode(12345);
        testBrewery.setBreweryWebsite("www.example.com");
        testBrewery.setActive(true);

        Beer testBeer = new Beer();
        testBeer.setBeerName("TEST");
        testBeer.setBeerStyle("TEST LAGER");
        testBeer.setBeerDescription("TASTES LIKE A TEST");
        testBeer.setBeerAbv(new BigDecimal("7.5"));
        testBeer.setBreweryId(TEST_BREWERY_ID);
        testBeer.setBeerImage("www.example.com");
        testBeer.setActive(true);

        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        reviewSqlDAO = new ReviewSqlDAO(jdbcTemplate);
        BrewerySqlDAO brewerySqlDAO = new BrewerySqlDAO(jdbcTemplate);
        BeerSqlDAO beerSqlDAO = new BeerSqlDAO(jdbcTemplate);

        brewerySqlDAO.update(testBrewery, TEST_BREWERY_ID);
        beerSqlDAO.update(testBeer, TEST_BEER_ID);
        reviewSqlDAO.update(TEST_REVIEW, VALID_ID);
    }

    @Test
    public void createNewReviewTest_creates_a_new_review() {
        int startingSize = reviewSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        reviewSqlDAO.create(TEST_REVIEW);

        int endingSize = reviewSqlDAO.listByBreweryId(TEST_BREWERY_ID).size();
        Assert.assertEquals(startingSize + 1, endingSize);
    }

    @Test(expected = Exception.class)
    public void createNewReviewTest_will_not_create_invalid_review() {
        TEST_REVIEW.setBeerId(INVALID_ID);

        reviewSqlDAO.create(TEST_REVIEW);
    }

    @Test
    public void listByBreweryIdTest_returns_same_size_list_when_run_twice() {
        List<Review> first = reviewSqlDAO.listByBreweryId(TEST_BREWERY_ID);
        List<Review> second = reviewSqlDAO.listByBreweryId(TEST_BREWERY_ID);

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void listByBeerIdTest_returns_same_size_list_when_run_twice() {
        List<Review> first = reviewSqlDAO.listByBeerId(TEST_BEER_ID);
        List<Review> second = reviewSqlDAO.listByBeerId(TEST_BEER_ID);

        Assert.assertEquals(first.size(), second.size());
    }

    @Test
    public void findByIdTest_returns_proper_record() throws ReviewNotFoundException {
        Review actual = reviewSqlDAO.findById(VALID_ID);

        Assert.assertEquals(VALID_ID, actual.getId());
    }

    @Test(expected = ReviewNotFoundException.class)
    public void findByIdTest_will_not_return_invalid_record() throws ReviewNotFoundException {
        reviewSqlDAO.findById(INVALID_ID);
    }

    @Test
    public void updateReviewTest_updates_a_review() throws ReviewNotFoundException {
        String testText = "TEST TEST";
        Review reviewToUpdate = reviewSqlDAO.findById(VALID_ID);
        reviewToUpdate.setReviewText(testText);

        reviewSqlDAO.update(reviewToUpdate, reviewToUpdate.getId());
        Review updatedReview = reviewSqlDAO.findById(VALID_ID);

        Assert.assertEquals(testText, updatedReview.getReviewText());
    }

    @Test(expected = ReviewNotFoundException.class)
    public void deactivateReviewTest_deactivates_a_review() throws ReviewNotFoundException {
        reviewSqlDAO.deactivate(VALID_ID);

        reviewSqlDAO.findById(VALID_ID);
    }
}