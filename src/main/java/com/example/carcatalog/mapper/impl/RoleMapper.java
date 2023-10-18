package com.example.carcatalog.mapper.impl;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleDTO> {
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Role toModel(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }

    @Override
    public RoleDTO toDTO(Role model) {
        return modelMapper.map(model, RoleDTO.class);
    }
}
