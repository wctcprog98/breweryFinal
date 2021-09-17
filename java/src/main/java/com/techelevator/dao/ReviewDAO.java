package com.techelevator.dao;

import com.techelevator.Exceptions.ReviewNotFoundException;
import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDAO {

    public void create(Review review);
    public List<Review> listByBreweryId(Long breweryId);
    public List<Review> listByBeerId(Long beerId);
    public Review findById(Long reviewId) throws ReviewNotFoundException;
    public void update(Review review, Long reviewId) throws ReviewNotFoundException;
    public void deactivate(Long reviewId) throws ReviewNotFoundException;
}
