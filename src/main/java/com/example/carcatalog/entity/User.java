package com.example.carcatalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a user.
 * <p>
 *     The annotations are:
 *     <ul>
 *         <li>{@link Entity} - the class is an entity</li>
 *         <li>{@link Table} - the name of the table in the database</li>
 *         <li>{@link ManyToOne} - the relationship between the user and the role is many to one</li>
 *     </ul>
 *     The fields are:
 *     <ul>
 *         <li>{@link Boolean} isActive - whether the user is active</li>
 *         <li>{@link String} firstName - the first name of the user</li>
 *         <li>{@link String} lastName - the last name of the user</li>
 *         <li>{@link String} imageURL - the URL of the image of the user</li>
 *         <li>{@link String} password - the password of the user</li>
 *         <li>{@link String} username - the username of the user</li>
 *         <li>{@link Role} role - the role of the user</li>
 *     </ul>
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends AuditBaseEntity {

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
