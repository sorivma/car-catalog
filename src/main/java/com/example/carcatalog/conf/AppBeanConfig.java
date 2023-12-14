package com.example.carcatalog.conf;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import net.datafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Application bean configuration
 * <p>
 *     This class is used to configure beans for the application
 *     and to make them available for injection.
 *     <br>
 *     The beans are:
 *     <ul>
 *         <li>{@link Faker} - a library for generating fake data</li>
 *         <li>{@link ModelMapper} - a library for mapping objects</li>
 *         <li>{@link Validator} - a jakarta validator for objects</li>
 */
@Configuration
public class AppBeanConfig {
    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
}
