package com.example.carcatalog.mapper.impl;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class CarModelMapper implements Mapper<Model, ModelDTO> {
    private final ModelMapper modelMapper;

    public CarModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<Model, ModelDTO> modelPropertyMapper = modelMapper.createTypeMap(Model.class, ModelDTO.class);
        modelPropertyMapper.addMapping(
                model -> model.getBrand().getName(),
                ModelDTO::setBrandName
        );
    }

    @Override
    public Model toModel(ModelDTO dto) {
        return modelMapper.map(dto, Model.class);
    }

    @Override
    public ModelDTO toDTO(Model model) {
        return modelMapper.map(model, ModelDTO.class);
    }
}
