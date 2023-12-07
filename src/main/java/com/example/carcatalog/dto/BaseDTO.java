package com.example.carcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Base class for all DTOs.
 * Contains the id field.
 * Used for the view layer.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseDTO {
    private UUID id;
}
