package com.example.carcatalog.service;

import com.example.carcatalog.dto.OfferDTO;

import java.util.UUID;

public interface OfferService extends BaseService<OfferDTO, UUID>{
    void delete(UUID id);
}
