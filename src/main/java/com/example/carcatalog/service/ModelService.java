package com.example.carcatalog.service;

import com.example.carcatalog.dto.ModelDTO;

import java.util.UUID;

public interface ModelService extends BaseService<ModelDTO, UUID> {
    ModelDTO update(ModelDTO modelDTO);
}
