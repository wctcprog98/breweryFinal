package com.techelevator.dao;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDAO  {

    void create(Brewery brewery);
    List<Brewery> listAll();
    Brewery findById(Long id) throws BreweryNotFoundException;
    void update(Brewery brewery, Long id) throws BreweryNotFoundException;
    void deactivate(Long id) throws BreweryNotFoundException;

}
