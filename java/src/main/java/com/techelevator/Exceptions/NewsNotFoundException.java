package com.techelevator.Exceptions;

public class NewsNotFoundException extends Exception {

    public NewsNotFoundException() {
        super("News not found!");
    }
}
