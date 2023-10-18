package com.example.carcatalog.service;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;

import java.util.UUID;

public interface RoleService extends BaseService<RoleDTO, UUID>{
    RoleDTO assign(UserDTO userDTO);
}
