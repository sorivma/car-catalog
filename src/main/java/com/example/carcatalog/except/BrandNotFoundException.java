package com.example.carcatalog.except;

import jakarta.persistence.EntityNotFoundException;

public class BrandNotFoundException extends EntityNotFoundException {
    public BrandNotFoundException() {
        super("No brand with provided name");
    }
}
