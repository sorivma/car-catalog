package com.example.carcatalog.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BrandDTO {
    private UUID id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;
}
