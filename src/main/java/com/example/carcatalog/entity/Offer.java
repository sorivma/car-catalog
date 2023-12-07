package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.EngineConverter;
import com.example.carcatalog.entity.converter.enumer.TransmissionConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Represents an offer.
 * <p>
 * The annotations are:
 * <ul>
 *     <li>{@link Entity} - the class is an entity</li>
 *     <li>{@link Table} - the name of the table in the database</li>
 *     <li>{@link Convert} - the {@link Engine} enum is converted to its ordinal value and vice versa</li>
 *     <li>{@link Convert} - the {@link Transmission} enum is converted to its ordinal value and vice versa</li>
 *     <li>{@link ManyToOne} - the relationship between the offer and the seller is many to one</li>
 *     <li>{@link ManyToOne} - the relationship between the offer and the model is many to one</li>
 * </ul>
 * The fields are:
 * <ul>
 *     <li>{@link String} description - the description of the offer</li>
 *     <li>{@link Engine} engine - the engine of the offer</li>
 *     <li>{@link String} imageURL - the URL of the image of the offer</li>
 *     <li>{@link Integer} mileage - the mileage of the offer</li>
 *     <li>{@link BigDecimal} price - the price of the offer</li>
 *     <li>{@link Transmission} transmission - the transmission of the offer</li>
 *     <li>{@link Integer} year - the year of the offer</li>
 *     <li>{@link User} seller - the seller of the offer</li>
 *     <li>{@link Model} model - the model of the offer</li>
 * </ul>
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offers")
@ToString
public class Offer extends AuditBaseEntity {
    private String description;
    @Convert(converter = EngineConverter.class)
    private Engine engine;
    private String imageURL;
    private Integer mileage;
    private BigDecimal price;
    @Convert(converter = TransmissionConverter.class)
    private Transmission transmission;
    private Integer year;
    @ManyToOne
    private User seller;
    @ManyToOne
    private Model model;

    public enum Engine implements OrdinalEnum {
        GASOLINE(10),
        DIESEL(20),
        ELECTRIC(30),
        HYBRID(40);
        private final Integer ordinal;

        Engine(Integer ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public Integer getOrdinal() {
            return ordinal;
        }
    }

    public enum Transmission implements OrdinalEnum {
        MANUAL(10),
        AUTOMATIC(20);

        private final Integer ordinal;

        Transmission(Integer ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public Integer getOrdinal() {
            return ordinal;
        }
    }
}
