package com.example.carcatalog.service.impl;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.except.BrandNotFoundException;
import com.example.carcatalog.except.NoBrandNameException;
import com.example.carcatalog.mapper.Mapper;
import com.example.carcatalog.repos.BrandRepository;
import com.example.carcatalog.service.BrandService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final Mapper<Brand, BrandDTO> brandMapper;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(Mapper<Brand, BrandDTO> brandMapper, BrandRepository brandRepository) {
        this.brandMapper = brandMapper;
        this.brandRepository = brandRepository;
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
                .orElseThrow(NoBrandNameException::new));
    }

    @Override
    public BrandDTO add(BrandDTO dto) {
        if (dto.getName() == null) {
            throw new NoBrandNameException();
        }

        Brand brand = brandMapper.toModel(dto);
        Optional<Brand> dbBrand = brandRepository.findBrandByName(brand.getName());

        if (dbBrand.isPresent()) {
            brand.setId(dbBrand.get().getId());
            brand.setModified(LocalDateTime.now());
            return brandMapper.toDTO(brandRepository.save(brand));
        }

        brand.setCreated(LocalDateTime.now());
        brand.setModified(LocalDateTime.now());

        return brandMapper.toDTO(brandRepository.save(brand));
    }

    @Override
    public BrandDTO findByName(BrandDTO name) throws EntityNotFoundException {
        return brandMapper.
                toDTO(brandRepository.findBrandByName(name.getName())
                        .orElseThrow(BrandNotFoundException::new));
    }

    @Override
    public void delete(UUID id) throws EntityNotFoundException {

    }
}
