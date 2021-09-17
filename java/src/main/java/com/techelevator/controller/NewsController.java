package com.techelevator.controller;

import com.techelevator.Exceptions.NewsNotFoundException;
import com.techelevator.dao.NewsSqlDAO;
import com.techelevator.model.News;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class NewsController {

    @Autowired
    NewsSqlDAO newsSqlDAO;

    @ApiOperation("Create a News post")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/news", method = RequestMethod.POST)
    public void create(@ApiParam(value = "News Object") @Valid @RequestBody News news) {
        newsSqlDAO.create(news);
    }

    @ApiOperation("List all News posts by Brewery ID")
    @RequestMapping(path = "/breweries/{id}/news", method = RequestMethod.GET)
    public List<News> listByBreweryId(@ApiParam(value = "Brewery ID") @PathVariable Long id) {
        return newsSqlDAO.listByBreweryId(id);
    }

    @ApiOperation("Return a News post by its ID")
    @RequestMapping(path = "/news/{id}", method = RequestMethod.GET)
    public News findById(@ApiParam(value = "News ID") @PathVariable Long id) throws NewsNotFoundException {
        return newsSqlDAO.findById(id);
    }

    @ApiOperation("Update a News post")
    @RequestMapping(path = "/news/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(value = "News Object") @Valid @RequestBody News news,
                       @ApiParam(value = "News ID") @PathVariable Long id) throws NewsNotFoundException {
        newsSqlDAO.update(news, id);
    }

    @ApiOperation("Deactivate a News post")
    @RequestMapping(path = "/news/{id}", method = RequestMethod.DELETE)
    public void deactivate(@ApiParam(value = "News ID") @PathVariable Long id) throws NewsNotFoundException {
        newsSqlDAO.deactivate(id);
    }
}
