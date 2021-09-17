package com.techelevator.Exceptions;

public class BeerNotFoundException extends Exception {
    public BeerNotFoundException() {
        super("Beer was not found!");
    }
}
