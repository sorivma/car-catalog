package com.example.carcatalog.init.impl;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.init.JsonReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserReader implements JsonReader<UserDTO> {
    @Override
    public List<UserDTO> readFromJson(String fileName) {
        return null;
    }
}
