package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.Offer;
import jakarta.persistence.Converter;

@Converter
public class TransmissionConverter extends OrdinalEnumConverter<Offer.Transmission>{
    @Override
    Class<Offer.Transmission> getEnumClass() {
        return Offer.Transmission.class;
    }
}
