package com.example.carcatalog.init.factory;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Offer;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.UserService;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * Factory for creating {@link OfferDTO} objects.
 * @see AbstractEntityFactory
 */
@Component
public class OfferFactory extends AbstractEntityFactory<OfferDTO>{
    private final Faker faker;
    private final List<Offer.Engine> engines = List.of(Offer.Engine.values());
    private final List<Offer.Transmission> transmissions = List.of(Offer.Transmission.values());
    private List<UserDTO> users;
    private List<ModelDTO> models;

    private UserService userService;
    private ModelService modelService;

    public OfferFactory(Faker faker) {
        this.faker = faker;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public OfferDTO getEntity() {
        if (users == null) {
            users = userService.findAll();
        }
        if (models == null) {
            models = modelService.findAll();
        }

        if (users.isEmpty() || models.isEmpty()) {
            throw new RuntimeException("getEntity() called with empty list of models or users");
        }

        Random random = new Random();
        return OfferDTO.builder()
                .engine(engines.get(random.nextInt(engines.size())))
                .transmission(transmissions.get(random.nextInt(transmissions.size())))
                .mileage(random.nextInt(200_000, 300_000))
                .price(new BigDecimal(random.nextLong(1_000_000L, 3_500_000L)))
                .year(random.nextInt(1990, 2020))
                .description(faker.vehicle().vin()
                        + " "
                        + faker.vehicle().makeAndModel()
                        + " "
                        + faker.vehicle().driveType())
                .imageURL(faker.internet().image(250, 250, faker.random().hex()))
                .modelUUID(models.get(random.nextInt(models.size())).getId())
                .sellerUsername(users.get(random.nextInt(users.size())).getUsername())
                .build();
    }

    @Override
    public int getMaxSize() {
        return 15_000;
    }
}
