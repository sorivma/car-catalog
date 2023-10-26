package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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
