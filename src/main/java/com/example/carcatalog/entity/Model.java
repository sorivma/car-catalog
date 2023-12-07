package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.CategoryConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a vehicle.
 * <p>
 *     The annotations are:
 *     <ul>
 *         <li>{@link Entity} - the class is an entity</li>
 *         <li>{@link Table} - the name of the table in the database</li>
 *         <li>{@link Convert} - the {@link Category} enum is converted to its ordinal value and vice versa</li>
 *         <li>{@link ManyToOne} - the relationship between the model and the brand is many to one</li>
 *         <li>{@link Column} - the name of the column in the database</li>
 *      </ul>
 *      The fields are:
 *      <ul>
 *          <li>{@link String} name - the name of the model</li>
 *          <li>{@link Category} category - the category of the model</li>
 *          <li>{@link String} imageURL - the URL of the image of the model</li>
 *          <li>{@link Integer} startYear - the year when the model was first produced</li>
 *          <li>{@link Integer} endYear - the year when the model was last produced</li>
 *          <li>{@link Brand} brand - the brand of the model</li>
 *      </ul>
 */
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
