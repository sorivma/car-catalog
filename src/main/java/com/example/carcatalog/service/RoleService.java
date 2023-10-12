package com.example.carcatalog.service;

import com.example.carcatalog.dto.RoleDTO;

import java.util.Set;

public interface RoleService {
    RoleDTO addRole(RoleDTO roleDTO);
    Set<RoleDTO> findAll();
}
