package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        USER
    }
}
