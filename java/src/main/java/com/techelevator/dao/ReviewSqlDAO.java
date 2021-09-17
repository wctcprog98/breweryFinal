package com.techelevator.dao;

import com.techelevator.Exceptions.ReviewNotFoundException;
import com.techelevator.model.Review;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ReviewSqlDAO implements ReviewDAO {

    private JdbcTemplate jdbcTemplate;

    public ReviewSqlDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Review review) {
        String sql = "INSERT INTO reviews (beer_id, reviewer_id, review_text, star_rating) " +
                     "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, review.getBeerId(), review.getReviewerId(), review.getReviewText(),
                                 review.getStarRating());
    }

    @Override
    public List<Review> listByBreweryId(Long breweryId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT reviews_id, reviews.beer_id, reviewer_id, review_text, star_rating, reviews.active " +
                     "FROM reviews JOIN beers ON reviews.beer_id = beers.beer_id " +
                                  "JOIN breweries ON beers.brewery_id = breweries.brewery_id " +
                                  "WHERE breweries.brewery_id = ? AND reviews.active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);

        while (results.next()) {
            Review review = mapRowToReview(results);
            reviews.add(review);
        }

        return reviews;
    }

    @Override
    public List<Review> listByBeerId(Long beerId){
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT reviews_id, beer_id, reviewer_id, review_text, star_rating, active " +
                     "FROM reviews WHERE beer_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);

        while (results.next()) {
            Review review = mapRowToReview(results);
            reviews.add(review);
        }

        return reviews;
    }

    @Override
    public Review findById(Long reviewId) throws ReviewNotFoundException {
        String sql  = "SELECT reviews_id, beer_id, reviewer_id, review_text, star_rating, active " +
                      "FROM reviews WHERE reviews_id = ? AND active = true";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, reviewId);

        if(result.next()){
            return mapRowToReview(result);
        }

        throw new ReviewNotFoundException();
    }

    @Override
    public void update(Review review, Long reviewId) throws ReviewNotFoundException {
        String sql = "UPDATE reviews SET beer_id = ?, reviewer_id = ?, review_text = ?, star_rating = ?, active = ?" +
                     "WHERE reviews_id = ?";

        try {
            jdbcTemplate.update(sql, review.getBeerId(), review.getReviewerId(),
                                     review.getReviewText(), review.getStarRating(), review.isActive(), reviewId);
        } catch (DataAccessException e) {
            throw new ReviewNotFoundException();
        }
    }

    @Override
    public void deactivate(Long reviewId) throws ReviewNotFoundException {
        String sql = "UPDATE reviews SET active = false WHERE reviews_id = ?";

        try {
            jdbcTemplate.update(sql, reviewId);
        } catch (DataAccessException e) {
            throw new ReviewNotFoundException();
        }
    }

    public Review mapRowToReview(SqlRowSet results) {
        Review review = new Review();

        review.setId(results.getLong("reviews_id"));
        review.setBeerId(results.getLong("beer_id"));
        review.setReviewerId(results.getLong("reviewer_id"));
        review.setReviewText(results.getString("review_text"));
        review.setStarRating(results.getBigDecimal("star_rating"));
        review.setActive(results.getBoolean("active"));

        return review;
    }
}
