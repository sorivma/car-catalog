package com.example.carcatalog.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

public interface JsonReader<T> {
     List<T> readFromJson(String fileName);
}
