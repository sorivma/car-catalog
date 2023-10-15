package com.example.carcatalog.mapper.impl;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User toModel(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDTO toDTO(User model) {
        return modelMapper.map(model, UserDTO.class);
    }
}
