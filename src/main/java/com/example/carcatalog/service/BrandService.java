package com.example.carcatalog.service;

import com.example.carcatalog.dto.BrandDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    BrandDTO add(BrandDTO brandDTO);

    List<BrandDTO> findAll();

    BrandDTO findById(UUID uuid) throws EntityNotFoundException;

    BrandDTO findByName(String name) throws EntityNotFoundException;
    BrandDTO deleteByName(String name) throws EntityNotFoundException;

    BrandDTO delete(UUID uuid);
}
