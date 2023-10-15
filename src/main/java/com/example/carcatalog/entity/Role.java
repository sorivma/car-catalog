package com.example.carcatalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseEntity {
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        USER
    }
}
