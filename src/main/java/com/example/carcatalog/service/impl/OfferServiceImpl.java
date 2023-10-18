package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.entity.Offer;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.ModelNotFoundException;
import com.example.carcatalog.except.OfferNotFoundException;
import com.example.carcatalog.except.UserNotFoundException;
import com.example.carcatalog.mapper.impl.OfferMapper;
import com.example.carcatalog.repos.ModelRepository;
import com.example.carcatalog.repos.OfferRepository;
import com.example.carcatalog.repos.UserRepository;
import com.example.carcatalog.service.OfferService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public OfferServiceImpl(UserRepository userRepository,
                            ModelRepository modelRepository,
                            OfferRepository offerRepository,
                            OfferMapper offerMapper) {
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
    }

    @Override
    public List<OfferDTO> findAll() {
        return offerRepository.findAllActive()
                .stream()
                .map(offerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OfferDTO findById(UUID uuid) {
        return offerMapper.toDTO(
                offerRepository.findById(uuid)
                        .orElseThrow(OfferNotFoundException::new)
        );
    }

    @Override
    public OfferDTO add(OfferDTO dto) {
        if (dto.getSellerUsername() == null) {
            throw new IllegalArgumentException("Seller username is not provided");
        }
        if (dto.getModelUUID() == null) {
            throw new IllegalArgumentException("Model UUID is not provided");
        }

        User seller = userRepository
                .findByUsername(dto.getSellerUsername())
                .orElseThrow(UserNotFoundException::new);
        Model model = modelRepository
                .findById(dto.getModelUUID())
                .orElseThrow(ModelNotFoundException::new);

        Offer offer = offerMapper.toModel(dto);
        offer.setSeller(seller);
        offer.setModel(model);
        offer.setCreated(LocalDateTime.now());

        return offerMapper.toDTO(offerRepository.save(offer));
    }

    @Override
    public void delete(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    public OfferDTO update(OfferDTO offerDTO) {
        if (offerDTO.getId() == null) {
            throw new IllegalArgumentException("Id shall be provided");
        }

        Offer dbOffer = offerRepository.findById(offerDTO.getId()).orElseThrow(OfferNotFoundException::new);
        Offer offer = offerMapper.toModel(offerDTO);
        offer.setSeller(dbOffer.getSeller());
        offer.setModel(dbOffer.getModel());
        offer.setCreated(dbOffer.getCreated());
        offer.setModified(LocalDateTime.now());
        return offerMapper.toDTO(offerRepository.save(offer));
    }
}
