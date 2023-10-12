package com.example.carcatalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offers")
public class Offer extends TimeBasedEntity {
    private String description;
    private Engine engine;
    private String imageURL;
    private Integer mileage;
    private BigDecimal price;
    private Transmission transmission;
    private LocalDateTime year;
    @ManyToOne
    private User seller;
    @ManyToOne
    private Model model;

    public Offer(LocalDateTime created,
                 LocalDateTime modified,
                 String description,
                 Engine engine,
                 String imageURL,
                 Integer mileage,
                 BigDecimal price,
                 Transmission transmission,
                 LocalDateTime year,
                 User user,
                 Model model) {
        super(created, modified);
        this.description = description;
        this.engine = engine;
        this.imageURL = imageURL;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.seller = user;
        this.model = model;
    }

    public enum Engine {
        GASOLINE,
        DIESEL,
        ELECTRIC,
        HYBRID
    }

    public enum Transmission {
        MANUAL,
        AUTOMATIC
    }
}
