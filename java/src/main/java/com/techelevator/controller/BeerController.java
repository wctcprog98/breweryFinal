package com.techelevator.controller;

import com.techelevator.Exceptions.BeerNotFoundException;
import com.techelevator.dao.BeerSqlDAO;
import com.techelevator.model.Beer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BeerController {

    @Autowired
    BeerSqlDAO beerSqlDAO;

    @ApiOperation("Create a new Beer")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/beer", method = RequestMethod.POST)
    public void create(@ApiParam(value = "Beer Object") @Valid @RequestBody Beer beer) {
        beerSqlDAO.create(beer);
    }

    @ApiOperation("List the top 5 highest rated Beers, system-wide")
    @RequestMapping(path = "/beer", method = RequestMethod.GET)
    public List<Beer> listTopRated() {
        return beerSqlDAO.listTopRated();
    }

    @ApiOperation("List all Beers by Brewery ID")
    @RequestMapping(path = "/breweries/{id}/beer", method = RequestMethod.GET)
    public List<Beer> listByBreweryId(@ApiParam(value = "Brewery ID") @PathVariable Long id) {
        return beerSqlDAO.listByBreweryId(id);
    }

    @ApiOperation("Return a Beer by its ID")
    @RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
    public Beer findById(@ApiParam(value = "Beer ID") @PathVariable Long id) throws BeerNotFoundException {
        return beerSqlDAO.findById(id);
    }

    @ApiOperation("Update a Beer")
    @RequestMapping(path = "/beer/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(value = "Beer Object") @Valid @RequestBody Beer beer,
                       @ApiParam(value = "Beer ID") @PathVariable Long id) throws BeerNotFoundException {
        beerSqlDAO.update(beer, id);
    }

    @ApiOperation("Deactivate a Beer")
    @RequestMapping(path = "/beer/{id}",method = RequestMethod.DELETE)
    public void deactivate(@ApiParam(value = "Beer ID") @PathVariable Long id) throws BeerNotFoundException {
        beerSqlDAO.deactivate(id);
    }
}


