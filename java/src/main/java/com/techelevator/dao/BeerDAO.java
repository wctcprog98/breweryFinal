package com.techelevator.dao;

import com.techelevator.Exceptions.BeerNotFoundException;
import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDAO {

    void create(Beer beer);
    List<Beer> listTopRated();
    List<Beer> listByBreweryId(long id);
    Beer findById(Long id) throws BeerNotFoundException;
    void update(Beer beer, Long id) throws BeerNotFoundException;
    void deactivate(Long id) throws BeerNotFoundException;
}
