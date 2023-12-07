package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Offer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for the {@link com.example.carcatalog.entity.Offer} entity.
 * Used for the view layer.
 * Contains the description, engine, imageURL, mileage, price, transmission, year, modelUUID, sellerUsername fields.
 * Extends {@link BaseDTO}.
 * <p>
 *     The annotations are used by the validator.
 *     <br>
 *     The annotations are:
 *     <ul>
 *         <li>{@link Positive} - the field must be positive</li>
 *         <li>{@link Enumerated} - the field must be one of the enum values</li>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OfferDTO extends BaseDTO {
    private String description;
    @Enumerated(EnumType.STRING)
    private Offer.Engine engine;
    private String imageURL;
    @Positive
    private Integer mileage;
    @Positive
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Offer.Transmission transmission;
    @Positive
    private Integer year;
    private UUID modelUUID;
    private String sellerUsername;
}
