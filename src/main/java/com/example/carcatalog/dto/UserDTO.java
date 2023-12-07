package com.example.carcatalog.dto;

import com.example.carcatalog.dto.BaseDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.utils.validation.annotation.Username;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * DTO for the {@link com.example.carcatalog.entity.User} entity.
 * Used for the view layer.
 * Contains the username, password, firstName, lastName, isActive, roleName, imageURL fields.
 * Extends {@link BaseDTO}.
 * <p>
 *     The annotations are used by the validator.
 *     <br>
 *     The annotations are:
 *     <ul>
 *         <li>{@link NotEmpty} - the field must not be empty</li>
 *     </ul>
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserDTO extends BaseDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Role.RoleName roleName;
    private String imageURL;
}
