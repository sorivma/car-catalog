package com.example.carcatalog.init.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.init.JsonReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferReader implements JsonReader<OfferDTO> {
    @Override
    public List<OfferDTO> readFromJson(String fileName) {
        return null;
    }
}
