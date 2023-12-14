package com.example.carcatalog.service;

import com.example.carcatalog.dto.OfferDTO;

import java.util.List;

public interface RecentService {
    List<OfferDTO> getRecentOffers();
}
