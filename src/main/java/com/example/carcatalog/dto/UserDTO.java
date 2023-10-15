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
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private Role role;
    private String imageURL;
    private LocalDateTime created;
    private LocalDateTime modified;
}
