package com.example.carcatalog.except;

public class NoBrandNameException extends IllegalArgumentException{
    public NoBrandNameException() {
        super("Brand name is required and cannot be empty");
    }
}
