package com.example.carcatalog.init;

import com.example.carcatalog.dto.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JsonReader<RoleDTO> roleReader;
    private final JsonReader<UserDTO> userReader;
    private final JsonReader<OfferDTO> offerReader;
    private final JsonReader<ModelDTO> modelReader;
    private final JsonReader<BrandDTO> brandReader;

    public DataInitializer(JsonReader<RoleDTO> roleReader,
                           JsonReader<UserDTO> userReader,
                           JsonReader<OfferDTO> offerReader,
                           JsonReader<ModelDTO> modelReader,
                           JsonReader<BrandDTO> brandReader) {
        this.roleReader = roleReader;
        this.userReader = userReader;
        this.offerReader = offerReader;
        this.modelReader = modelReader;
        this.brandReader = brandReader;
    }

    @Override
    public void run(String... args) throws Exception {
    }

    private void populateData() {
    }
}
