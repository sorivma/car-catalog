package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "models")
public class Model extends TimeBasedEntity {
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String imageURL;
    private Integer startYear;
    private Integer endYear;
    @ManyToOne
    private Brand brand;

    public Model(LocalDateTime created,
                 LocalDateTime modified,
                 String name,
                 Category category,
                 String imageURL,
                 Integer startYear,
                 Integer endYear,
                 Brand brand) {
        super(created, modified);
        this.name = name;
        this.category = category;
        this.imageURL = imageURL;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public enum Category {
        CAR,
        BUSS,
        TRUCK,
        MOTORCYCLE
    }
}
