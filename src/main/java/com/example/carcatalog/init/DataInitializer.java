package com.example.carcatalog.init;

import com.example.carcatalog.dto.*;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.entity.Offer;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.io.JsonReader;
import com.example.carcatalog.service.BrandService;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.RoleService;
import com.example.carcatalog.service.UserService;
import com.example.carcatalog.service.impl.OfferServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final RoleService roleService;
    private final UserService userService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final OfferServiceImpl offerService;
    private final JsonReader<RoleDTO> roleReader;
    private final JsonReader<UserDTO> userReader;
    private final JsonReader<BrandDTO> brandReader;
    private final JsonReader<ModelDTO> modelReader;

    public DataInitializer(
            RoleService roleService,
            UserService userService,
            BrandService brandService, ModelService modelService, OfferServiceImpl offerService, JsonReader<RoleDTO> roleReader,
            JsonReader<UserDTO> userReader, JsonReader<BrandDTO> brandReader, JsonReader<ModelDTO> modelReader) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
        this.roleReader = roleReader;
        this.userReader = userReader;
        this.brandReader = brandReader;
        this.modelReader = modelReader;

    }

    @Override
    public void run(String... args) {
        populateData();
    }

    private void populateData() {
        String FOLDER = "init/";

        // Init roles
        List<RoleDTO> roles = roleReader.readFromJson(FOLDER + "role.json", RoleDTO[].class);
        logger.info("Loaded roles: " + roles.toString());
        roles.forEach(roleService::add);

        // Init users
        List<UserDTO> users = userReader.readFromJson(FOLDER + "user.json", UserDTO[].class);
        logger.info("Loaded users: " + users.toString());
        users.forEach(userService::add);


        // Assign Admin role to user with username sorivma or user under index 0
        UserDTO ars = users.stream().filter((user) -> user.getUsername().equals("sorivma"))
                .findFirst()
                .orElse(users.get(0));
        ars.setRoleName(Role.RoleName.ADMIN);
        roleService.assign(ars);

        // Update user
        ars.setLastName("Дуброки");
        logger.info("Updated surname for user with username" + ars.getUsername() + " :" + userService.add(ars)
                .toString());

        // Deactivate user
        userService.deactivate(ars.getUsername());

        // Init brands
        List<BrandDTO> brands = brandReader.readFromJson(FOLDER + "brand.json", BrandDTO[].class);
        logger.info("Brands loaded: " + brands.toString());
        brands.forEach(brandService::add);

        // Update brand
        BrandDTO brandDTO = brandService.findByName(BrandDTO
                .builder()
                .name("LADA")
                .build());
        brandDTO.setName("LADA Test");
        BrandDTO updatedBrandDTO = brandService.add(brandDTO);
        logger.info("Brand with name LADA updated to" + updatedBrandDTO);

        // Init models
        List<ModelDTO> models = modelReader.readFromJson(FOLDER + "model.json", ModelDTO[].class);
        logger.info("Models loaded: " + models.toString());
        models.forEach(modelService::add);

        // Update model
        ModelDTO modelDTO = modelService.findAll().get(0);
        modelDTO.setName(modelDTO.getName() + " Test modified");
        modelDTO.setBrandName("BMW");
        modelDTO.setCategory(Model.Category.CAR);
        ModelDTO savedModel = modelService.update(modelDTO);
        logger.info("Model updated: " + savedModel);

        // Init offers
        OfferDTO offerDTO = OfferDTO.builder()
                .engine(Offer.Engine.HYBRID)
                .sellerUsername("sorivma")
                .modelUUID(modelService.findAll().get(0).getId())
                .build();
        OfferDTO savedOffer = offerService.add(offerDTO);


        savedOffer.setDescription("Cool car");
        savedOffer.setEngine(Offer.Engine.GASOLINE);
        savedOffer.setImageURL("image url");
        savedOffer.setPrice(new BigDecimal(100000000000L));
        savedOffer.setTransmission(Offer.Transmission.MANUAL);
        savedOffer.setMileage(1000);
        savedOffer.setYear(2010);
        System.out.println(offerService.update(savedOffer));

        // delete actions test
//        offerService.delete(savedOffer.getId());
//        brandService.delete(updatedBrandDTO.getId());

    }
}
