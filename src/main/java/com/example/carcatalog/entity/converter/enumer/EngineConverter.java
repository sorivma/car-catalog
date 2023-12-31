package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.Offer;
import jakarta.persistence.Converter;

/**
 * Converts the {@link Offer.Engine} enum to its ordinal value and vice versa.
 */
@Converter
public class EngineConverter extends OrdinalEnumConverter<Offer.Engine> {
    @Override
    Class<Offer.Engine> getEnumClass() {
        return Offer.Engine.class;
    }
}
