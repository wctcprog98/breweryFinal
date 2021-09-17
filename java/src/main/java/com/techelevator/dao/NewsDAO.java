package com.techelevator.dao;

import com.techelevator.Exceptions.NewsNotFoundException;
import com.techelevator.model.News;

import java.util.List;

public interface NewsDAO {

    public void create(News news);
    public List<News> listByBreweryId(Long breweryId);
    public News findById(Long newsId) throws NewsNotFoundException;
    public void update(News news, Long newsId) throws NewsNotFoundException;
    public void deactivate(Long newsId) throws NewsNotFoundException;
}
