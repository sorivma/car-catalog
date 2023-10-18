package com.example.carcatalog.except;

import jakarta.persistence.EntityNotFoundException;

public class ModelNotFoundException extends EntityNotFoundException {
    public ModelNotFoundException() {
        super("No model with provided id");
    }
}
