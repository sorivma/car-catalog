package com.example.carcatalog.service;

import com.example.carcatalog.dto.BrandDTO;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface BrandService {
    BrandDTO addBrand(BrandDTO brand);
    Set<BrandDTO> findAll();
    Optional<BrandDTO> findByName(String name);
}
