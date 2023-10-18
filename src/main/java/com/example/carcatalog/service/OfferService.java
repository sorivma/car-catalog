package com.example.carcatalog.service;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.entity.Offer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OfferService extends BaseService<OfferDTO, UUID>{
    void delete(UUID id);
    OfferDTO update(OfferDTO offerDTO);
}
