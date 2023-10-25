package com.example.carcatalog.entity.converter.enumer;

import com.example.carcatalog.entity.Role;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter extends OrdinalEnumConverter<Role.RoleName> {
    @Override
    Class<Role.RoleName> getEnumClass() {
        return Role.RoleName.class;
    }
}
