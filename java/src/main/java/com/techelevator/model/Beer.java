package com.techelevator.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Beer {

    private Long id;
    @NotBlank(message = "Name field cannot be blank")
    private String beerName;
    @NotBlank(message = "Style field cannot be blank")
    private String beerStyle;
    @NotBlank(message = "Description field cannot be blank")
    private String beerDescription;
    @NotNull(message = "ABV field cannot be blank")
    @DecimalMin(value = "0.1", message = "ABV must be greater than 0")
    private BigDecimal beerAbv;
    @NotNull(message = "BreweryId field cannot be blank")
    private Long breweryId;
    private String beerImage;
    private boolean active;

    public Beer() {

    }

    public Beer(Long id, String beerName, String beerStyle, String beerDescription,
                BigDecimal beerAbv, Long breweryId, String beerImage) {
        this.id = id;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.beerDescription = beerDescription;
        this.beerAbv = beerAbv;
        this.breweryId = breweryId;
        this.beerImage = beerImage;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getBeerDescription() {
        return beerDescription;
    }

    public void setBeerDescription(String beerDescription) {
        this.beerDescription = beerDescription;
    }

    public BigDecimal getBeerAbv() {
        return beerAbv;
    }

    public void setBeerAbv(BigDecimal beerAbv) {
        this.beerAbv = beerAbv;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public String getBeerImage() {
        return beerImage;
    }

    public void setBeerImage(String beerImage) {
        this.beerImage = beerImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", beerName='" + beerName + '\'' +
                ", beerStyle='" + beerStyle + '\'' +
                ", beerDescription='" + beerDescription + '\'' +
                ", beerAbv=" + beerAbv +
                ", breweryId=" + breweryId +
                ", active=" + active +
                '}';
    }
}
