package com.example.carcatalog.service;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.except.ClientErrorException;

import java.util.UUID;

/**
 * Service interface for Role entity.
 */
public interface RoleService extends BaseService<RoleDTO, UUID>{
    /**
     * Assigns a role to a user.
     * @param userDTO the user DTO
     * @return the user DTO
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    UserDTO assign(UserDTO userDTO) throws ClientErrorException.EntityNotFoundException;
    /**
     * Returns role by name.
     * @param name the name
     * @return role
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    Role findByName(Role.RoleName name);
}
