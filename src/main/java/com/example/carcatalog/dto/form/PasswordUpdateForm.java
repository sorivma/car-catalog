package com.example.carcatalog.dto.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordUpdateForm {
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
}
