package com.example.carcatalog.service;

import com.example.carcatalog.dto.UserDTO;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    Set<UserDTO> findAll();
    Optional<UserDTO> findByUsername(String username);
}
