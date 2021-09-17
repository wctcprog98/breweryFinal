package com.techelevator.dao;

import com.techelevator.Exceptions.BeerNotFoundException;
import com.techelevator.model.Beer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class BeerSqlDAO implements BeerDAO{

    private JdbcTemplate jdbcTemplate;

    public BeerSqlDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Beer beerToAdd) {
       String sql = "INSERT INTO beers (beer_name, beer_style, beer_description, image, beer_abv, brewery_id) " +
               " VALUES (?, ?, ?, ?, ?, ?)";

       jdbcTemplate.update(sql, beerToAdd.getBeerName(), beerToAdd.getBeerStyle(), beerToAdd.getBeerDescription(),
                                beerToAdd.getBeerImage(), beerToAdd.getBeerAbv(), beerToAdd.getBreweryId());
    }

    @Override
    public List<Beer> listTopRated(){
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT beers.beer_id, beer_name, beer_style, beer_description, image, beer_abv, " +
                            "brewery_id, beers.active " +
                            "FROM beers JOIN reviews ON reviews.beer_id = beers.beer_id " +
                            "WHERE beers.active = true AND reviews.active = true " +
                            "GROUP BY beers.beer_id ORDER BY AVG(reviews.star_rating) DESC LIMIT 5;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }

        return beers;
    }

    @Override
    public List<Beer> listByBreweryId(long id) {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT beer_id, beer_name, beer_style, beer_description, image, beer_abv, brewery_id, active " +
                     "FROM beers WHERE brewery_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while (results.next()) {
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }
        return beers;
    }

    @Override
    public Beer findById(Long id) throws BeerNotFoundException {
        String sql = "Select beer_id, beer_name, beer_style, beer_description, image, beer_abv, brewery_id, active " +
                     "FROM beers WHERE beer_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if(results.next()) {
            return mapRowToBeer(results);
        }
        throw new BeerNotFoundException();

    }

    @Override
    public void update(Beer beer, Long id) throws BeerNotFoundException {
        String sql = "UPDATE beers SET beer_name = ?, beer_style = ?, beer_description = ?, image = ?, " +
                                      "beer_abv = ?, brewery_id = ?, active = ? WHERE beer_id = ?";
        try {
            jdbcTemplate.update(sql, beer.getBeerName(), beer.getBeerStyle(), beer.getBeerDescription(),
                                     beer.getBeerImage(), beer.getBeerAbv(), beer.getBreweryId(), beer.isActive(), id);
        } catch (DataAccessException e) {
            throw new BeerNotFoundException();
        }
    }

    @Override
    public void deactivate(Long id) throws BeerNotFoundException {
        String sql = "UPDATE beers SET active = false WHERE beer_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new BeerNotFoundException();
        }
    }

    private Beer mapRowToBeer(SqlRowSet rs) {
        Beer beer = new Beer();

        beer.setId(rs.getLong("beer_id"));
        beer.setBeerName(rs.getString("beer_name"));
        beer.setBeerStyle(rs.getString("beer_style"));
        beer.setBeerDescription(rs.getString("beer_description"));
        beer.setBeerImage(rs.getString("image"));
        beer.setBeerAbv(rs.getBigDecimal("beer_abv"));
        beer.setBreweryId(rs.getLong("brewery_id"));
        beer.setActive(rs.getBoolean("active"));

        return beer;
    }
}
