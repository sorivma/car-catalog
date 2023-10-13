package com.example.carcatalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table()
public class Model extends TimeBasedEntity {
    @Column(nullable = false)
    private String name;
    private Category category;
    private String imageURL;
    private LocalDateTime startYear;
    private LocalDateTime endYear;
    @ManyToOne
    private Brand brand;

    public Model(LocalDateTime created,
                 LocalDateTime modified,
                 String name,
                 Category category,
                 String imageURL,
                 LocalDateTime startYear,
                 LocalDateTime endYear,
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
