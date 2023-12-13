package com.example.carcatalog.service;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Model;

import java.util.List;
import java.util.UUID;
/**
 * Service interface for Model entity.
 */
public interface ModelService extends BaseService<ModelDTO, UUID> {
    /**
     * Returns an entity by UUID.
     * Is a domain method that should be used only in the service layer.
     * @param modelUUID the UUID
     * @return the entity
     */
    Model finEntityById(UUID modelUUID);

    List<ModelDTO> getBrandModels(String brandName);
}
