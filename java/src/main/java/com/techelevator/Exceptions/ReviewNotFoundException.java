package com.techelevator.Exceptions;

public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
        super("Review was not found!");
    }
}
