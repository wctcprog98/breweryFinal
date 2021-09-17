package com.techelevator.dao;

import com.techelevator.Exceptions.NewsNotFoundException;
import com.techelevator.model.News;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class NewsSqlDAO implements NewsDAO {

    private JdbcTemplate jdbcTemplate;

    public NewsSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(News news) {
        String sql = "INSERT INTO news (news_name, news_body, brewery_id) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, news.getNewsName(), news.getNewsBody(), news.getBreweryId());
    }

    @Override
    public List<News> listByBreweryId(Long breweryId) {
        List<News> news = new ArrayList<>();
        String sql = "SELECT news_id, news_name, news_body, brewery_id, active " +
                     "FROM news WHERE brewery_id = ? AND active = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);

        while (results.next()) {
            News thisNews = mapRowToNews(results);
            news.add(thisNews);
        }

        return news;
    }

    @Override
    public News findById(Long newsId) throws NewsNotFoundException {
        String sql = "SELECT news_id, news_name, news_body, brewery_id, active " +
                     "FROM news WHERE news_id = ? AND active = true";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, newsId);

        if (result.next()) {
            return mapRowToNews(result);
        }

        throw new NewsNotFoundException();
    }

    @Override
    public void update(News news, Long newsId) throws NewsNotFoundException {
        String sql = "UPDATE news SET news_name = ?, news_body = ?, brewery_id = ?, active = ? WHERE news_id = ?";

        try {
            jdbcTemplate.update(sql, news.getNewsName(), news.getNewsBody(), news.getBreweryId(),
                                     news.isActive(), newsId);
        } catch (DataAccessException e) {
            throw new NewsNotFoundException();
        }
    }

    @Override
    public void deactivate(Long newsId) throws NewsNotFoundException {
        String sql = "UPDATE news SET active = false WHERE news_id = ?";

        try {
            jdbcTemplate.update(sql, newsId);
        } catch (DataAccessException e) {
            throw new NewsNotFoundException();
        }
    }

    public News mapRowToNews(SqlRowSet results) {
        News news = new News();

        news.setId(results.getLong("news_id"));
        news.setNewsName(results.getString("news_name"));
        news.setNewsBody(results.getString("news_body"));
        news.setBreweryId(results.getLong("brewery_id"));
        news.setActive(results.getBoolean("active"));

        return news;
    }
}
