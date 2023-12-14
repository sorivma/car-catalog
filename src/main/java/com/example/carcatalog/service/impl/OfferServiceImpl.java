package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.viewmodel.OfferViewModel;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.entity.Offer;
import com.example.carcatalog.entity.User;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.mapper.impl.OfferMapper;
import com.example.carcatalog.repos.OfferRepository;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.OfferService;
import com.example.carcatalog.service.UserService;
import com.example.carcatalog.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private ModelService modelService;
    private UserService userService;
    private final OfferMapper offerMapper;
    private final ValidationUtil validator;

    public OfferServiceImpl(OfferMapper offerMapper, ValidationUtil validator) {
        this.offerMapper = offerMapper;
        this.validator = validator;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    @Cacheable("offers")
    public List<OfferDTO> findAll() {
        return offerRepository.findAllActive()
                .stream()
                .map(offerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OfferDTO findById(UUID uuid) {
        return offerMapper.toDTO(
                offerRepository.findById(uuid)
                        .orElseThrow(() ->
                                new ClientErrorException.EntityNotFoundException("Offer", "id", uuid.toString()))
        );
    }

    @Override
    // TODO: make aspect for handling validation
    public OfferDTO add(OfferDTO dto) {
        if (validator.isInvalid(dto)) {
            throw new IllegalArgumentException("Invalid arguments: " + validator.violations(dto));
        }

        User seller = userService.findEntityByUserName(dto.getSellerUsername());
        Model model = modelService.finEntityById(dto.getModelUUID());

        Offer offer = offerMapper.toModel(dto);
        offer.setSeller(seller);
        offer.setModel(model);

        return offerMapper.toDTO(offerRepository.save(offer));
    }

    @Override
    public List<OfferDTO> addAll(List<OfferDTO> dtoList) {
        List<Offer> offers = dtoList.stream().map(offerMapper::toModel).toList();
        return offerRepository.saveAll(offers).stream().map(offerMapper::toDTO).toList();
    }

    @Override
    public void delete(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "offer", key = "#id")
    public OfferViewModel getOfferViewModel(UUID id) {
        OfferDTO offerDTO = findById(id);

        return new OfferViewModel(
                offerDTO,
                userService.findByUserName(offerDTO.getSellerUsername()),
                modelService.findById(offerDTO.getModelUUID())
        );
    }

    @Override
    @Cacheable(value = "modelOffers", key = "#id")
    public List<OfferDTO> getModelOffers(UUID id) {
        return findAll().stream().filter(offerDTO -> offerDTO.getModelUUID().equals(id)).toList();
    }

    @Override
    @Cacheable(value = "userOffers", key = "#username")
    public List<OfferDTO> getUserOffers(String username) {
        return findAll().stream().filter(offerDTO -> offerDTO.getSellerUsername().equals(username)).toList();
    }
}
