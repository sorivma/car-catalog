package com.example.carcatalog.init.factory;

import com.example.carcatalog.dto.BrandDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Factory for creating {@link BrandDTO} objects.
 * @see AbstractEntityFactory
 */
@Component
public class BrandFactory extends AbstractEntityFactory<BrandDTO> {
    private final List<String> brandNames;
    private int curName = 0;

    public BrandFactory(ObjectMapper objectMapper) {
        try {
            ClassPathResource resource = new ClassPathResource("init/brandName.json");
            this.brandNames = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file and initialize list of brand names");
        }
    }
    @Override
    public BrandDTO getEntity() {
        return BrandDTO.builder()
                .name(brandNames.get(curName++))
                .build();
    }

    @Override
    public int getMaxSize() {
        return brandNames.size();
    }
}
