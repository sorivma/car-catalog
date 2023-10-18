package com.example.carcatalog.except;

public class InvalidIdException extends IllegalArgumentException {
    public InvalidIdException() {
        super("Invalid id provided");
    }
}
