package com.example.carcatalog.service;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService extends BaseService<UserDTO, UUID> {
    void deactivate(UserDTO userDTO);
    UserDTO findByUserName(UserDTO userDTO);
}
