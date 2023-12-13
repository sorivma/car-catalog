package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.mapper.impl.CarModelMapper;
import com.example.carcatalog.repos.BrandRepository;
import com.example.carcatalog.repos.ModelRepository;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private final CarModelMapper carModelMapper;
    private final ValidationUtil validator;

    public ModelServiceImpl(ModelRepository modelRepository, CarModelMapper carModelMapper,
                            ValidationUtil validator) {
        this.modelRepository = modelRepository;
        this.carModelMapper = carModelMapper;
        this.validator = validator;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<ModelDTO> findAll() {
        return modelRepository.findAll()
                .stream()
                .map(carModelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ModelDTO findById(UUID uuid) {
        return carModelMapper.toDTO(finEntityById(uuid));
    }

    @Override
    // TODO: make aspect for handling validation
    public ModelDTO add(ModelDTO dto) {
        if (validator.isInvalid(dto)) {
            throw new IllegalArgumentException("Invalid arguments: " + validator.violations(dto));
        }

        Model model = carModelMapper.toModel(dto);
        model.setBrand(brandRepository.findBrandByName(dto.getBrandName()).orElseThrow(
                () -> new ClientErrorException.EntityNotFoundException("Brand", "name", dto.getBrandName())));
        return carModelMapper.toDTO(modelRepository.save(model));
    }

    @Override
    public List<ModelDTO> addAll(List<ModelDTO> dtoList) {
        List<Model> models = dtoList.stream().map(carModelMapper::toModel).toList();
        return modelRepository.saveAll(models).stream().map(carModelMapper::toDTO).toList();
    }

    @Override
    public Model finEntityById(UUID modelUUID) {
        return modelRepository.findById(modelUUID).orElseThrow(
                () -> new ClientErrorException
                        .EntityNotFoundException("Model", "id", modelUUID.toString()));
    }

    @Override
    public List<ModelDTO> getBrandModels(String brandName) {
        return modelRepository.findByBrandName(brandName)
                .stream()
                .map(carModelMapper::toDTO)
                .collect(Collectors.toList());
    }
}
