package com.example.carcatalog.mapper.impl;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper implements Mapper<Brand, BrandDTO> {
    private final ModelMapper modelMapper;

    public BrandMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Brand toModel(BrandDTO dto) {
        return modelMapper.map(dto, Brand.class);
    }

    @Override
    public BrandDTO toDTO(Brand model) {
        return modelMapper.map(model, BrandDTO.class);
    }
}
