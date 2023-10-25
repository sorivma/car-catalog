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

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offers")
@ToString
public class Offer extends TimeBasedEntity {
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

    public enum Engine implements OrdinalEnum{
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

    public enum Transmission implements OrdinalEnum{
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
