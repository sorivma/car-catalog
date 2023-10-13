package com.example.carcatalog.init.impl;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.init.JsonReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelReader implements JsonReader<ModelDTO> {
    @Override
    public List<ModelDTO> readFromJson(String fileName) {
        return null;
    }
}
