package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO extends BaseDTO{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Role.RoleName roleName;
    private String imageURL;
}
