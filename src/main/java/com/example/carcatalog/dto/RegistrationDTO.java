package com.example.carcatalog.dto;

import com.example.carcatalog.utils.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @NotEmpty
    @UniqueUsername
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    private String lastName;
    private String imageURL;
}
