package com.example.carcatalog.init;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Holds the properties for the {@link DataInitializer}.
 * The properties are read from the {@code application.properties} file.
 * The prefix for the properties is {@code initializer}.
 * The properties are:
 * <ul>
 *     <li>{@code enabled} - whether the data initializer is enabled or not</li>
 *     <li>{@code userNum} - the number of users to be created</li>
 *     <li>{@code brandNum} - the number of brands to be created</li>
 *     <li>{@code modelNum} - the number of models to be created</li>
 *     <li>{@code offerNum} - the number of offers to be created</li>
 */
@Component
@ConfigurationProperties(prefix = "initializer")
@Setter
@Getter
public class DataInitializerProperties {
    private boolean enabled = false;
    private Integer userNum = 100;
    private Integer brandNum = 64;
    private Integer modelNum = 100;
    private Integer offerNum = 100;
}
