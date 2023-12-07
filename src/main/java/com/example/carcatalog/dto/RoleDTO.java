package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

/**
 * DTO for the {@link com.example.carcatalog.entity.Role} entity.
 * Used for the view layer.
 * Contains the name field.
 * Extends {@link BaseDTO}.
 * <p>
 *     The annotations are used by the validator.
 *     <br>
 *     The annotations are:
 *     <ul>
 *         <li>{@link Enumerated} - the field must be one of the enum values</li>
 *     </ul>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoleDTO extends BaseDTO{
    @Enumerated(EnumType.STRING)
    private Role.RoleName name;
}
