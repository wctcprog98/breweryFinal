package com.techelevator.Exceptions;

public class EventNotFoundException extends Exception {
    public EventNotFoundException() {
        super("Event was not found!");
    }
}
