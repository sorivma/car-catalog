package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Offer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class OfferDTO {
    private UUID id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Offer.Engine engine;
    private String imageURL;
    private Integer mileage;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Offer.Transmission transmission;
    private Integer year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private UUID modelUUID;
    private String sellerUsername;
}
