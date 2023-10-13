package com.example.carcatalog.init.impl;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.init.JsonReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleReader implements JsonReader<RoleDTO> {
    @Override
    public List<RoleDTO> readFromJson(String fileName) {
        return null;
    }
}
