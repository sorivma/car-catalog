package com.example.carcatalog.service;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.viewmodel.OfferViewModel;
import com.example.carcatalog.except.ClientErrorException;

import java.util.List;
import java.util.UUID;
/**
 * Service interface for Offer entity.
 */
public interface OfferService extends BaseService<OfferDTO, UUID>{
    /**
     * Deletes an entity by ID.
     * @param id the ID
     * @throws ClientErrorException.EntityNotFoundException if the entity is not found
     */
    void delete(UUID id) throws ClientErrorException.EntityNotFoundException;

    /**
     * Returns a view model for offer.
     * @param id the ID
     * @return the view model
     */
    OfferViewModel getOfferViewModel(UUID id);

    List<OfferDTO> getModelOffers(UUID id);
    List<OfferDTO> getUserOffers(String username);

    OfferDTO update(OfferDTO dto);
}
