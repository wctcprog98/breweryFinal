package com.techelevator.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Brewery {

    private Long id;
    @NotBlank(message = "Name field must no be blank")
    private String breweryName;
    @NotNull(message = "Brewer ID field must not be blank")
    @Min(value = 1, message = "Invalid Brewer")
    private Long brewerId;
    private String breweryStreetAddress;
    private String breweryCity;
    private String breweryState;
    private int breweryZipCode;
    private String breweryWebsite;
    private boolean active;

    public Brewery(){

    }

    public Brewery(Long id, String breweryName, Long brewerId, String breweryStreetAddress, String breweryCity,
                   String breweryState, int breweryZipCode, String breweryWebsite) {
        this.id = id;
        this.breweryName = breweryName;
        this.brewerId = brewerId;
        this.breweryStreetAddress = breweryStreetAddress;
        this.breweryCity = breweryCity;
        this.breweryState = breweryState;
        this.breweryZipCode = breweryZipCode;
        this.breweryWebsite = breweryWebsite;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public Long getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(Long brewerId) {
        this.brewerId = brewerId;
    }

    public String getBreweryStreetAddress() {
        return breweryStreetAddress;
    }

    public void setBreweryStreetAddress(String breweryStreetAddress) {
        this.breweryStreetAddress = breweryStreetAddress;
    }

    public String getBreweryCity() {
        return breweryCity;
    }

    public void setBreweryCity(String breweryCity) {
        this.breweryCity = breweryCity;
    }

    public String getBreweryState() {
        return breweryState;
    }

    public void setBreweryState(String breweryState) {
        this.breweryState = breweryState;
    }

    public int getBreweryZipCode() {
        return breweryZipCode;
    }

    public void setBreweryZipCode(int breweryZipCode) {
        this.breweryZipCode = breweryZipCode;
    }

    public String getBreweryWebsite() {
        return breweryWebsite;
    }

    public void setBreweryWebsite(String breweryWebsite) {
        this.breweryWebsite = breweryWebsite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "id=" + id +
                ", breweryName='" + breweryName + '\'' +
                ", brewerId=" + brewerId +
                ", breweryStreetAddress='" + breweryStreetAddress + '\'' +
                ", breweryCity='" + breweryCity + '\'' +
                ", breweryState='" + breweryState + '\'' +
                ", breweryZipCode=" + breweryZipCode +
                ", isActive=" + active +
                ", breweryWebsite='" + breweryWebsite + '\'' +
                '}';
    }
}

