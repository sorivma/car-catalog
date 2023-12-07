package com.example.carcatalog.service;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Brand;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for Brand entity.
 */
public interface BrandService extends BaseService<BrandDTO, UUID> {
    /**
     * Returns an entity by name.
     * @param name the name
     * @return the entity
     * @throws EntityNotFoundException if the entity is not found
     */
    Brand findByName(String name) throws EntityNotFoundException;
    /**
     * Deletes an entity by ID.
     * @param id the ID
     * @throws EntityNotFoundException if the entity is not found
     */
    void delete(UUID id) throws EntityNotFoundException;

    List<ModelDTO> findModelsByName(String name);
}
