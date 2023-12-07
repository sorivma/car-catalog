package com.example.carcatalog.init.factory;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.service.BrandService;
import com.example.carcatalog.service.ModelService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Factory for creating {@link ModelDTO} objects.
 * @see AbstractEntityFactory
 */
@Component
public class ModelFactory extends AbstractEntityFactory<ModelDTO> {
    private final Faker faker;
    private final List<Model.Category> categories = List.of(Model.Category.values());
    private BrandService brandService;
    private List<BrandDTO> brands;

    public ModelFactory(Faker faker) {
        this.faker = faker;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public ModelDTO getEntity() {
        if (brands == null) {
            brands = brandService.findAll();
        }

        if (brands.isEmpty()) {
            throw new RuntimeException("getEntity() called with empty list of models");
        }

        Random random = new Random();
        return ModelDTO.builder()
                .category(categories.get(random.nextInt(categories.size())))
                .brandName(brands.get(random.nextInt(brands.size())).getName())
                .imageURL(faker.internet().image())
                .name(faker.vehicle().model())
                .startYear(random.nextInt(2010, 2015))
                .endYear(random.nextInt(2016, 2024))
                .build();
    }

    @Override
    public int getMaxSize() {
        return 15_000;
    }
}
