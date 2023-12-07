package com.example.carcatalog.service;

import com.example.carcatalog.except.ClientErrorException;

import java.util.List;

/**
 * Base service interface for all services.
 * @param <T> the DTO type
 * @param <ID> the ID type
 */
public interface BaseService<T, ID> {
    /**
     * Returns all entities.
     * @return all entities
     */
    List<T> findAll();

    /**
     * Returns an entity by ID.
     * @param id the ID
     * @return the entity
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    T findById(ID id) throws ClientErrorException.EntityNotFoundException;

    /**
     * Adds an entity.
     * @param dto the DTO
     * @return the added entity
     */
    T add(T dto);

    /**
     * Adds a collection of entities.
     * @param dtoList the collection of DTOs
     * @return the collection of added entities
     */
    List<T> addAll(List<T> dtoList);
}
