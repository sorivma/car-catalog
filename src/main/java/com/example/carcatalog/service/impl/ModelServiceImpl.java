package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.except.BrandNotFoundException;
import com.example.carcatalog.except.InvalidIdException;
import com.example.carcatalog.except.ModelNotFoundException;
import com.example.carcatalog.except.NoBrandNameException;
import com.example.carcatalog.mapper.impl.CarModelMapper;
import com.example.carcatalog.repos.BrandRepository;
import com.example.carcatalog.repos.ModelRepository;
import com.example.carcatalog.service.ModelService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    private final CarModelMapper carModelMapper;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, CarModelMapper carModelMapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.carModelMapper = carModelMapper;
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
        return carModelMapper.toDTO(modelRepository.findById(uuid).orElseThrow(ModelNotFoundException::new));
    }

    @Override
    public ModelDTO add(ModelDTO dto) {
        if (dto.getBrandName() == null) {
            throw new NoBrandNameException();
        }
        if (dto.getName() == null) {
            throw new IllegalArgumentException("No model name provided");
        }

        Model model = carModelMapper.toModel(dto);
        if (model.getId() == null) {
            model.setCreated(LocalDateTime.now());
        }
        model.setModified(LocalDateTime.now());
        model.setBrand(brandRepository.findBrandByName(dto.getBrandName()).orElseThrow(BrandNotFoundException::new));
        return carModelMapper.toDTO(modelRepository.save(model));
    }

    @Override
    public ModelDTO update(ModelDTO modelDTO) {
        if (modelDTO.getId() == null) {
            throw new IllegalArgumentException("ID shall be provided");
        }
        if (modelDTO.getBrandName() == null) {
            throw new NoBrandNameException();
        }
        if (modelDTO.getName() == null) {
            throw new IllegalArgumentException("No model name provided");
        }

        Optional<Model> dbModel = modelRepository.findById(modelDTO.getId());
        if (dbModel.isEmpty()) {
            throw new ModelNotFoundException();
        }

        Model model = carModelMapper.toModel(modelDTO);
        model.setCreated(dbModel.get().getCreated());
        model.setModified(LocalDateTime.now());
        model.setBrand(brandRepository.findBrandByName(modelDTO.getBrandName())
                .orElseThrow(BrandNotFoundException::new));

        return carModelMapper.toDTO(modelRepository.save(model));
    }
}
