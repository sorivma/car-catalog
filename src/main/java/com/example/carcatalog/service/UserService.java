package com.example.carcatalog.service;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.ClientErrorException;

import java.util.UUID;
/**
 * Service interface for User entity.
 */
public interface UserService extends BaseService<UserDTO, UUID> {
    /**
     * Deactivates a user.
     * @param username the username
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    void deactivate(String username) throws ClientErrorException.EntityNotFoundException;

    /**
     * Returns a user by username
     * @param username the username
     * @return the user
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    UserDTO findByUserName(String username) throws ClientErrorException.EntityNotFoundException;
    /**
     * Returns a user by username
     * Is a domain method that should be used only in the service layer.
     * @param sellerUsername the username
     * @return the user
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    User findEntityByUserName(String sellerUsername);


    /**
     * Updates user details.
     * username shall present in the userDTO
     * @param userDTO the userDTO with the updated details
     * @return the updated userDTO
     */
    UserDTO update(UserDTO userDTO);

    /**
     * Checks if a username is taken.
     * @param username the username
     * @return true if the username is taken, false otherwise
     */
    Boolean isUsernameTaken(String username);
}
