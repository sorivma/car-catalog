package com.example.carcatalog.except;

import jakarta.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException() {
        super("No role with provided name");
    }
}
