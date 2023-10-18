package com.example.carcatalog.except;

public class NoUsernameException extends IllegalArgumentException{
    public NoUsernameException() {
        super("Username is required and cannot be empty");
    }
}
