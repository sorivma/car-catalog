package com.example.carcatalog.dto;

import com.example.carcatalog.conf.ApplicationConstants;
import com.example.carcatalog.entity.Offer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @Pattern(regexp = ApplicationConstants.ValidationConstants.UUID_PATTERN,
            message = "Invalid UUID pattern for model ID")
    private UUID modelUUID;
    @Min(5)
    private String sellerUsername;
}
