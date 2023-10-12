package com.example.carcatalog.service;

import com.example.carcatalog.dto.OfferDTO;

import java.util.Optional;
import java.util.Set;

public interface OfferService {
    OfferDTO addOffer(OfferDTO offer);
    Set<OfferDTO> findAll();
    Optional<OfferDTO> findById();
}
