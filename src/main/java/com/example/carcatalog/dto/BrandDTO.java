package com.example.carcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandDTO {
    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
}
