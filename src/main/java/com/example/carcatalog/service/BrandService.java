package com.example.carcatalog.service;

import com.example.carcatalog.dto.BrandDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;

import java.util.UUID;

public interface BrandService extends BaseService<BrandDTO, UUID> {

    BrandDTO findByName(BrandDTO name) throws EntityNotFoundException;
    void delete(UUID id) throws EntityNotFoundException;
}
