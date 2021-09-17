package com.techelevator.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

public class Event {

    private Long id;
    @NotBlank(message = "Name field must not be blank")
    private String eventName;
    @NotNull(message = "Date field must not be blank")
    @Future(message = "Event Date must be in the future")
    private Date eventDate;
    private Time eventTime;
    @NotBlank(message = "Description field must not be blank")
    private String eventDescription;
    @NotNull(message = "BreweryID field must not be blank")
    @Min(value = 1, message = "Invalid Brewery ID")
    private Long breweryId;
    private boolean active;

    public Event() {
    }

    public Event(Long id, String eventName, Date eventDate, Time eventTime, String eventDescription, Long breweryId) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.breweryId = breweryId;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", eventDescription='" + eventDescription + '\'' +
                ", breweryId=" + breweryId +
                ", active=" + active +
                '}';
    }
}
