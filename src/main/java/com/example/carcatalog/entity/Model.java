package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.CategoryConverter;
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
public class Model extends AuditBaseEntity {
    @Column(nullable = false)
    private String name;
    @Convert(converter = CategoryConverter.class)
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

    public enum Category implements OrdinalEnum {
        CAR(10),
        BUSS(20),
        TRUCK(30),
        MOTORCYCLE(40);

        private final Integer ordinal;

        Category(Integer ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public Integer getOrdinal() {
            return ordinal;
        }
    }
}
