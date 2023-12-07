package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.RoleConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Represents a role in the application.
 * <p>
 * The annotations are:
 * <ul>
 *     <li>{@link Entity} - the class is an entity</li>
 *     <li>{@link Table} - the name of the table in the database</li>
 *     <li>{@link OneToMany} - the relationship between the role and the users is one to many</li>
 *     <li>{@link Convert} - the {@link RoleName} enum is converted to its ordinal value and vice versa</li>
 *     <li>{@link RoleConverter} - the {@link RoleName} enum is converted to its ordinal value and vice versa</li>
 *     <li>{@link RoleName} - the enum that represents the role name</li>
 *     <li>{@link OrdinalEnum} - the interface that is implemented by the {@link RoleName} enum</li>
 * </ul>
 * The fields are:
 * <ul>
 *     <li>{@link RoleName} name - the name of the role</li>
 *     <li>{@link Set} - the users with this role</li>
 * </ul>
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
public class Role extends BaseEntity {
    @Convert(converter = RoleConverter.class)
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    /**
     * Represents the role name.
     */
    public enum RoleName implements OrdinalEnum {
        /**
         * The admin role.
         */
        ADMIN(10),
        /**
         * The user role.
         * <p>
         * The user role is the default role.
         * </p>
         */
        USER(20);
        private final Integer ordinal;

        RoleName(Integer ordinal) {
            this.ordinal = ordinal;
        }

        @Override
        public Integer getOrdinal() {
            return ordinal;
        }
    }
}
