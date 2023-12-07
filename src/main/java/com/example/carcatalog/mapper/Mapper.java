package com.example.carcatalog.mapper;

/**
 * Mapper interface for mapping between DTOs and models.
 * Is a good place to put additional mapping logic.
 * @param <M> the model type
 * @param <D> the DTO type
 */
public interface Mapper<M, D> {
    /**
     * Maps from DTO to model.
     * @param dto the DTO
     * @return the model
     */
    M toModel(D dto);

    /**
     * Maps from model to DTO.
     * @param model the model
     * @return the DTO
     */
    D toDTO(M model);
}
