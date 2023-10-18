package com.example.carcatalog.io;

import java.util.List;

public interface JsonReader<T> {
     List<T> readFromJson(String fileName, Class<T[]> clazz);
}
