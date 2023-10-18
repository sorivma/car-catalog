package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoleDTO {
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Role.RoleName name;
}
