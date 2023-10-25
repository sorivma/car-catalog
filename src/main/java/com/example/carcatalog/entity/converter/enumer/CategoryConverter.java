package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.Model;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter extends OrdinalEnumConverter<Model.Category>{
    @Override
    Class<Model.Category> getEnumClass() {
        return Model.Category.class;
    }
}
