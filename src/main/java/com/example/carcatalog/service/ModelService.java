package com.example.carcatalog.service;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Model;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ModelService extends BaseService<ModelDTO, UUID> {
    void updateModel(ModelDTO modelDTO);
    
}
