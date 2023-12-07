package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a brand.
 * <p>
 *     The annotations are:
 *     <ul>
 *         <li>{@link Entity} - the class is an entity</li>
 *         <li>{@link Table} - the name of the table in the database</li>
 *         <li>{@link OneToMany} - the relationship between the brand and the models is one to many</li>
 *     </ul>
 *     The fields are:
 *     <ul>
 *         <li>{@link String} name - the name of the brand</li>
 *         <li>{@link Set} models - the models of the brand</li>
 *     </ul>
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "brands")
public class Brand extends AuditBaseEntity {
    public Brand(LocalDateTime created,
                 LocalDateTime modified,
                 String name) {
        super(created, modified);
        this.name = name;
    }

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private Set<Model> models;
}
