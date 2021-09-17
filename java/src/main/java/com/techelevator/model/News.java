package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class News {

    private Long id;
    @NotBlank(message = "Name field must not be blank")
    private String newsName;
    @NotBlank(message = "Body field must not be blank")
    private String newsBody;
    @NotNull(message = "BreweryID field must not be blank")
    @Min(value = 1, message = "Invalid Brewery ID")
    private Long breweryId;
    private boolean active;

    public News() {
    }

    public News(Long id, String newsName, String newsBody, Long breweryId) {
        this.id = id;
        this.newsName = newsName;
        this.newsBody = newsBody;
        this.breweryId = breweryId;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsName='" + newsName + '\'' +
                ", newsBody='" + newsBody + '\'' +
                ", breweryId=" + breweryId +
                ", active=" + active +
                '}';
    }
}
