package com.example.carcatalog.init.impl;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.init.JsonReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandReader implements JsonReader<BrandDTO> {
    @Override
    public List<BrandDTO> readFromJson(String fileName) {
        return null;
    }
}
