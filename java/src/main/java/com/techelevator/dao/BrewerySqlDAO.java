package com.techelevator.dao;

import com.techelevator.Exceptions.BreweryNotFoundException;
import com.techelevator.model.Brewery;
import com.techelevator.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class BrewerySqlDAO implements BreweryDAO {

    private JdbcTemplate jdbcTemplate;

    public BrewerySqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Brewery breweryToAdd) {
        String sql = "INSERT INTO breweries (brewery_name, brewer_id, brewery_street_address, brewery_city, " +
                     "brewery_state, brewery_zip, brewery_website) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, breweryToAdd.getBreweryName(), breweryToAdd.getBrewerId(),
                breweryToAdd.getBreweryStreetAddress(), breweryToAdd.getBreweryCity(), breweryToAdd.getBreweryState(),
                breweryToAdd.getBreweryZipCode(), breweryToAdd.getBreweryWebsite());
    }

    @Override
    public List<Brewery> listAll() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT brewery_id, brewery_name, brewer_id, brewery_street_address, brewery_city, " +
                            "brewery_state, brewery_zip, brewery_website, active FROM breweries WHERE active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Brewery brewery = mapRowToBrewery(results);
            breweries.add(brewery);
        }

        return breweries;
    }

    @Override
    public Brewery findById(Long breweryId) throws BreweryNotFoundException {
        String sql = "SELECT brewery_id, brewery_name, brewer_id, brewery_street_address, brewery_city, " +
                            "brewery_state, brewery_zip, brewery_website, active FROM breweries " +
                     "WHERE brewery_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);

        if(results.next()){
            return mapRowToBrewery(results);
        }
        throw new BreweryNotFoundException();
    }

    @Override
    public void update(Brewery brewery, Long id) throws BreweryNotFoundException {
        String sql = "UPDATE breweries SET brewery_name = ?, brewer_id = ?, brewery_street_address = ?, " +
                     "brewery_city = ?, brewery_state = ?, brewery_zip = ?, brewery_website = ?, active = ? " +
                     "WHERE brewery_id = ?";
        try {
            jdbcTemplate.update(sql, brewery.getBreweryName(), brewery.getBrewerId(), brewery.getBreweryStreetAddress(),
                                     brewery.getBreweryCity(), brewery.getBreweryState(), brewery.getBreweryZipCode(),
                                     brewery.getBreweryWebsite(),brewery.isActive(), id);
        } catch (DataAccessException e) {
            throw new BreweryNotFoundException();
        }
    }

    @Override
    public void deactivate(Long breweryId) throws BreweryNotFoundException {
        String sql = "UPDATE breweries SET active = false WHERE brewery_id = ?";
        try {
            jdbcTemplate.update(sql, breweryId);
        } catch (DataAccessException e){
                    throw new BreweryNotFoundException();
        }
    }

    private Brewery mapRowToBrewery(SqlRowSet results){
        Brewery brewery = new Brewery();

        brewery.setId(results.getLong("brewery_id"));
        brewery.setBreweryName(results.getString("brewery_name"));
        brewery.setBrewerId(results.getLong("brewer_id"));
        brewery.setBreweryStreetAddress(results.getString("brewery_street_address"));
        brewery.setBreweryCity(results.getString("brewery_city"));
        brewery.setBreweryState(results.getString("brewery_state"));
        brewery.setBreweryZipCode(results.getInt("brewery_zip"));
        brewery.setBreweryWebsite(results.getString("brewery_website"));
        brewery.setActive(results.getBoolean("active"));

        return brewery;
    }

}
