package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends TimeBasedEntity {

    public User(LocalDateTime created,
                LocalDateTime modified,
                Boolean isActive,
                String firstName,
                String lastName,
                String imageURL,
                String password,
                String username,
                Role role) {
        super(created, modified);
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    private Boolean isActive;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String password;
    @Column(unique = true, nullable = false)
    private String username;
    @ManyToOne
    private Role role;
}
