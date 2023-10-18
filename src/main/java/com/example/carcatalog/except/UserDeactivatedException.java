package com.example.carcatalog.except;

public class UserDeactivatedException extends IllegalArgumentException {
    public UserDeactivatedException() {
        super("Deactivated user update is prohibited");
    }
}
