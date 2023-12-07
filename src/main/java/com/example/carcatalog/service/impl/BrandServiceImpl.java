package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.repos.BrandRepository;
import com.example.carcatalog.service.BrandService;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.utils.validation.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final Mapper<Brand, BrandDTO> brandMapper;
    private final ValidationUtil validator;
    private BrandRepository brandRepository;
    private ModelService modelService;

    public BrandServiceImpl(Mapper<Brand, BrandDTO> brandMapper, ValidationUtil validator) {
        this.brandMapper = brandMapper;
        this.validator = validator;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(brandMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findById(UUID uuid) {
        return brandMapper.toDTO(brandRepository
                .findById(uuid)
                .orElseThrow(
                        () -> new ClientErrorException.EntityNotFoundException("Brand", "id", uuid.toString())
                ));
    }

    @Override
    // TODO: make aspect for handling validation
    public BrandDTO add(BrandDTO dto) {
        if (validator.isInvalid(dto)) {
            throw new IllegalArgumentException("Invalid arguments provided");
        }
        Brand brand = brandMapper.toModel(dto);
        return brandMapper.toDTO(brandRepository.save(brand));
    }

    @Override
    public List<BrandDTO> addAll(List<BrandDTO> dtoList) {
        List<Brand> brands = dtoList.stream().map(brandMapper::toModel).toList();
        return brandRepository.saveAll(brands).stream().map(brandMapper::toDTO).toList();
    }

    @Override
    public Brand findByName(String name) throws EntityNotFoundException {
        return brandRepository.findBrandByName(name).orElseThrow(
                () -> new ClientErrorException.EntityNotFoundException("Brand", "name", name));
    }

    @Override
    public void delete(UUID id) throws EntityNotFoundException {
        brandRepository.deleteById(id);
    }

    @Override
    public List<ModelDTO> findModelsByName(String name) {
        return modelService.findAll().stream().filter(modelDTO -> modelDTO.getBrandName().equals(name))
                .collect(Collectors.toList());
    }
}
