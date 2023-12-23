package com.example.carcatalog.init;

import com.example.carcatalog.dto.*;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.init.factory.BrandFactory;
import com.example.carcatalog.init.factory.ModelFactory;
import com.example.carcatalog.init.factory.OfferFactory;
import com.example.carcatalog.init.factory.UserFactory;
import com.example.carcatalog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Populates the database with initial data.
 * The data is populated only if the property {@code data.initializer.enabled} is set to {@code true}.
 * Has next dependencies:
 * <ul>
 *     <li>{@link RoleService}</li>
 *     <li>{@link UserService}</li>
 *     <li>{@link BrandService}</li>
 *     <li>{@link ModelService}</li>
 *     <li>{@link OfferService}</li>
 *     <li>{@link UserFactory}</li>
 *     <li>{@link BrandFactory}</li>
 *     <li>{@link ModelFactory}</li>
 *     <li>{@link OfferFactory}</li>
 *     <li>{@link DataInitializerProperties}</li>
 *     <li>{@link Logger}</li>
 * </ul>
 */
@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private RoleService roleService;
    private UserService userService;
    private BrandService brandService;
    private ModelService modelService;
    private OfferService offerService;
    private final UserFactory userFactory;
    private final BrandFactory brandFactory;
    private final ModelFactory modelFactory;
    private final OfferFactory offerFactory;
    private final PasswordEncoder passwordEncoder;
    private final DataInitializerProperties dataInitializerProperties;

    public DataInitializer(UserFactory userFactory,
                           BrandFactory brandFactory,
                           ModelFactory modelFactory,
                           OfferFactory offerFactory, PasswordEncoder passwordEncoder,
                           DataInitializerProperties dataInitializerProperties) {
        this.userFactory = userFactory;
        this.brandFactory = brandFactory;
        this.modelFactory = modelFactory;
        this.offerFactory = offerFactory;
        this.passwordEncoder = passwordEncoder;
        this.dataInitializerProperties = dataInitializerProperties;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    public void run(String... args) {
        if (dataInitializerProperties.isEnabled()) {
            logger.info("Data Initializer enabled");
            populateData();
        }
    }

    private void populateData() {
        populateRoles();
        populateUsers();
        populateBrands();
        populateModels();
        populateOffers();
        createTestUser();
    }

    private void populateRoles() {
        RoleDTO adminRole = new RoleDTO();
        adminRole.setName(Role.RoleName.ADMIN);

        RoleDTO userRole = new RoleDTO();
        userRole.setName(Role.RoleName.USER);

        roleService.add(adminRole);
        roleService.add(userRole);

        logger.info("Roles initialized");
    }

    private void populateUsers() {
        List<RegistrationDTO> userDTOS = userFactory.getEntities(dataInitializerProperties
                .getUserNum());
        logger.info("Generated: {} users", userDTOS.size());
        userDTOS.forEach(userService::register);
        logger.info("Saved: {} users", userDTOS.size());
    }

    private void populateBrands() {
        List<BrandDTO> brandDTOS = brandFactory.getEntities(dataInitializerProperties
                .getBrandNum());
        logger.info("Generated: {} brands", brandDTOS.size());
        brandDTOS.forEach(brandService::add);
        logger.info("Saved: {} brands", brandDTOS.size());
    }

    private void populateModels() {
        List<ModelDTO> modelDTOS = modelFactory.getEntities(dataInitializerProperties
                .getModelNum());
        logger.info("Generated: {} models", modelDTOS.size());
        modelDTOS.forEach(modelService::add);
        logger.info("Saved: {} models", modelDTOS.size());
    }

    private void populateOffers() {
        List<OfferDTO> offerDTOS = offerFactory.getEntities(dataInitializerProperties
                .getOfferNum());
        logger.info("Generated: {} offers", offerDTOS.size());
        offerDTOS.forEach(offerService::add);
        logger.info("Saved: {} offers", offerDTOS.size());
    }

    private void createTestUser() {
        if (dataInitializerProperties.isTestUser()) {
            logger.info("Generated test user with name: {} and password {}", dataInitializerProperties.getTestUsername(),
                    dataInitializerProperties.getTestPassword());
            userService.register(RegistrationDTO.builder()
                            .username(dataInitializerProperties.getTestUsername())
                            .password(dataInitializerProperties
                                    .getTestPassword())
                            .imageURL("https://www.pngitem.com/pimgs/m/137-1370051_avatar-generic-avatar-hd-png-download.png")
                            .firstName("Test")
                            .lastName("User")
                    .build());

            UserDTO userDTO = userService.findByUserName(dataInitializerProperties.getTestUsername());
            userDTO.setRoleName(Role.RoleName.ADMIN);
            roleService.assign(userDTO);
            logger.info("Added test user");

        }
    }
}
