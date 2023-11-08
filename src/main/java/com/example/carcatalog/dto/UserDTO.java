package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserDTO extends BaseDTO {
    @NotEmpty
    @Min(5)
    private String username;
    @NotEmpty
    @Min(8)
    private String password;
    @NotEmpty
    @Min(3)
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Role.RoleName roleName;
    private String imageURL;
}
