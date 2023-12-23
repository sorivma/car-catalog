package com.example.carcatalog.mapper.impl;

import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.entity.BaseEntity;
import com.example.carcatalog.entity.Offer;
import com.example.carcatalog.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper implements Mapper<Offer, OfferDTO> {
    private final ModelMapper modelMapper;

    public OfferMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<Offer, OfferDTO> offerPropertyMapper = modelMapper.createTypeMap(Offer.class, OfferDTO.class);
        TypeMap<OfferDTO, Offer> offerPropertyMapperRev = modelMapper.createTypeMap(OfferDTO.class, Offer.class);
        offerPropertyMapper.addMapping(
                offer -> offer.getSeller().getUsername(),
                OfferDTO::setSellerUsername
        );

        offerPropertyMapper.addMapping(
                offer -> offer.getModel().getId(),
                OfferDTO::setModelUUID
        );

        offerPropertyMapper.addMapping(
                Offer::getId,
                OfferDTO::setId
        );

        offerPropertyMapperRev.addMapping(
                OfferDTO::getId,
                Offer::setId
        );
    }

    @Override
    public Offer toModel(OfferDTO dto) {
        return modelMapper.map(dto, Offer.class);
    }

    @Override
    public OfferDTO toDTO(Offer model) {
        return modelMapper.map(model, OfferDTO.class);
    }
}
