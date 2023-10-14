package com.example.carcatalog.service;

import com.example.carcatalog.dto.ModelDTO;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;

import java.util.List;

public interface ModelService {
    List<ModelDTO> findAll();
    ModelDTO findById() throws EntityNotFoundException, EntityActionVetoException;
    ModelDTO add(ModelDTO modelDTO);
    ModelDTO update(ModelDTO modelDTO);

}
