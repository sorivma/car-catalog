package com.example.carcatalog.init.factory;

import com.example.carcatalog.dto.UserDTO;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Factory for creating {@link UserDTO} objects.
 * @see AbstractEntityFactory
 */
@Component
public class UserFactory extends AbstractEntityFactory<UserDTO> {
    private final Faker faker;
    private final Set<String> usedUsernames = new HashSet<>();

    public UserFactory(Faker faker) {
        this.faker = faker;
    }

    @Override
    public UserDTO getEntity() {
        String username;
        do {
            username = faker.name().username().split("\\.")[0];
        } while (!usedUsernames.add(username));

        return UserDTO.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .password(faker.internet().password())
                .username(username)
                .imageURL(faker.internet().image())
                .build();
    }

    @Override
    public int getMaxSize() {
        return 15_000;
    }
}
