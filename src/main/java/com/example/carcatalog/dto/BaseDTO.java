package com.example.carcatalog.dto;

import com.example.carcatalog.conf.ApplicationConstants;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDTO {
    private UUID id;
}
