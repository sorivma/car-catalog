package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.Role;
import jakarta.persistence.Converter;

/**
 * Converts the {@link Role.RoleName} enum to its ordinal value and vice versa.
 */
@Converter
public class RoleConverter extends OrdinalEnumConverter<Role.RoleName> {
    @Override
    Class<Role.RoleName> getEnumClass() {
        return Role.RoleName.class;
    }
}
