package com.example.carcatalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "brands")
public class Brand extends TimeBasedEntity {
    public Brand(LocalDateTime created,
                 LocalDateTime modified,
                 String name) {
        super(created, modified);
        this.name = name;
    }

    @Column(unique = true)
    private String name;
}
