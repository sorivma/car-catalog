package com.example.carcatalog.service;

import com.example.carcatalog.dto.ModelDTO;

import java.util.Optional;
import java.util.Set;

public interface ModelService {
    ModelDTO addModel(ModelDTO modelDTO);
    Set<ModelDTO> findAll();
    Optional<ModelDTO> findById();
}
