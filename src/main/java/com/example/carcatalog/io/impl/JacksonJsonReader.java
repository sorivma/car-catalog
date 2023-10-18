package com.example.carcatalog.io.impl;

import com.example.carcatalog.io.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class JacksonJsonReader<T> implements JsonReader<T> {
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public JacksonJsonReader(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    public List<T> readFromJson(String fileName, Class<T[]> clazz) {
        Resource resource = new ClassPathResource(fileName);
        try {
            InputStreamReader reader = new InputStreamReader(resource.getInputStream(),
                    StandardCharsets.UTF_8);
            String json = FileCopyUtils.copyToString(reader);

            T[] array = objectMapper.readValue(json, clazz);

            return Arrays.asList(array);
        } catch (IOException e) {
            logger.error("Io exception: " + e.getMessage());
            return null;
        }
    }
}
