package com.example.carcatalog.except;

public class NoRoleException extends IllegalArgumentException {
    public NoRoleException() {
        super("Role name is required and cannot be empty");
    }
}
