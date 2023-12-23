package com.example.carcatalog.dto.form;

import com.example.carcatalog.utils.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UsernameUpdateForm {
    @NotBlank
    @Length(min = 3, max = 15)
    @UniqueUsername
    private String username;
}
