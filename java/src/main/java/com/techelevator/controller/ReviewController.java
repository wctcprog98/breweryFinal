package com.techelevator.controller;

import com.techelevator.Exceptions.ReviewNotFoundException;
import com.techelevator.dao.ReviewSqlDAO;
import com.techelevator.model.Review;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewSqlDAO reviewSqlDAO;

    @ApiOperation("Create a new Review")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/reviews", method = RequestMethod.POST)
    public void create(@ApiParam(value = "Review Object") @Valid @RequestBody Review review) {
        reviewSqlDAO.create(review);
    }

    @ApiOperation("List all Reviews by Brewery ID")
    @RequestMapping(path = "/breweries/{id}/reviews", method = RequestMethod.GET)
    public List<Review> listByBreweryId(@ApiParam(value = "Brewery ID") @PathVariable Long id) {
        return reviewSqlDAO.listByBreweryId(id);
    }

    @ApiOperation("List all Reviews by Beer ID")
    @RequestMapping(path = "/beer/{id}/reviews", method = RequestMethod.GET)
    public List<Review> listByBeerId(@ApiParam(value = "Beer ID") @PathVariable Long id) {
        return reviewSqlDAO.listByBeerId(id);
    }

    @ApiOperation("Return a Review by its ID")
    @RequestMapping(path = "/reviews/{id}", method = RequestMethod.GET)
    public Review findById(@ApiParam(value = "Review ID") @PathVariable Long id) throws ReviewNotFoundException {
        return reviewSqlDAO.findById(id);
    }

    @ApiOperation("Update a Review")
    @RequestMapping(path = "/reviews/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(value = "Review Object") @Valid @RequestBody Review review,
                       @ApiParam(value = "Review ID") @PathVariable Long id) throws ReviewNotFoundException {
        reviewSqlDAO.update(review, id);
    }

    @ApiOperation("Deactivate a Review")
    @RequestMapping(path = "/reviews/{id}", method = RequestMethod.DELETE)
    public void deactivate(@ApiParam(value = "Review ID") @PathVariable Long id) throws ReviewNotFoundException {
        reviewSqlDAO.deactivate(id);
    }

}
