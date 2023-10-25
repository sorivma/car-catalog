package com.example.carcatalog.entity;

import com.example.carcatalog.entity.converter.enumer.RoleConverter;
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
    @Convert(converter = RoleConverter.class)
    private RoleName name;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public enum RoleName implements OrdinalEnum{
        ADMIN(10),
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
