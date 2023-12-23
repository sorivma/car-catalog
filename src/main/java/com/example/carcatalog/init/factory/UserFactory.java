package com.example.carcatalog.init.factory;

import com.example.carcatalog.dto.RegistrationDTO;
import com.example.carcatalog.dto.UserDTO;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Factory for creating {@link UserDTO} objects.
 * @see AbstractEntityFactory
 */
@Component
public class UserFactory extends AbstractEntityFactory<RegistrationDTO> {
    private final Faker faker;
    private final Set<String> usedUsernames = new HashSet<>();
    private final PasswordEncoder passwordEncoder;

    public UserFactory(Faker faker, PasswordEncoder passwordEncoder) {
        this.faker = faker;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegistrationDTO getEntity() {
        String username;
        do {
            username = faker.name().username().split("\\.")[0];
        } while (!usedUsernames.add(username));

        return RegistrationDTO.builder()
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
