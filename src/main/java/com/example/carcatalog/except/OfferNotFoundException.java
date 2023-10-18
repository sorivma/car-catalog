package com.example.carcatalog.except;

import jakarta.persistence.EntityNotFoundException;

public class OfferNotFoundException extends EntityNotFoundException {
    public OfferNotFoundException() {
        super("No offer found with provided id");
    }
}
