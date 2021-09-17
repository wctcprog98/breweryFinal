package com.techelevator.controller;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.dao.BrewerySqlDAO;
import com.techelevator.model.Brewery;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BreweryController {

    @Autowired
    BrewerySqlDAO brewerySqlDAO;

    @ApiOperation("Create a new Brewery")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path ="/breweries", method = RequestMethod.POST)
    public void create(@ApiParam(value = "Brewery Object") @Valid @RequestBody Brewery brewery) {
        brewerySqlDAO.create(brewery);
    }

    @ApiOperation("List all Breweries, system-wide")
    @RequestMapping(path = "/breweries", method = RequestMethod.GET)
    public List<Brewery> listAll() {
       return brewerySqlDAO.listAll();
    }

    @ApiOperation("Return a Brewery by its ID")
    @RequestMapping(path ="/breweries/{id}", method = RequestMethod.GET)
    public Brewery findById(@ApiParam(value = "Brewery ID") @PathVariable Long id) throws
            BreweryNotFoundException {
        return brewerySqlDAO.findById(id);
    }

    @ApiOperation("Update a Brewery")
    @RequestMapping(path = "/breweries/{id}", method = RequestMethod.PUT)
    public void update(@ApiParam(value = "Brewery Object") @Valid @RequestBody Brewery brewery,
                       @ApiParam(value = "Brewery ID") @PathVariable Long id) throws BreweryNotFoundException {
        brewerySqlDAO.update(brewery, id);
    }

    @ApiOperation("Deactivate a Brewery")
    @RequestMapping(path ="/breweries/{id}", method = RequestMethod.DELETE)
    public void deactivate(@ApiParam(value = "Brewery ID") @PathVariable Long id) throws BreweryNotFoundException {
        brewerySqlDAO.deactivate(id);
    }
}
