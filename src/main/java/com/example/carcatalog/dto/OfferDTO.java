package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Offer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferDTO {
    private UUID id;
    private String description;
    private Offer.Engine engine;
    private String imageURL;
    private Integer mileage;
    private BigDecimal price;
    private Offer.Transmission transmission;
    private LocalDateTime year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private ModelDTO model;
    private UserDTO seller;
}
