package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.entity.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ModelDTO {
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Model.Category category;
    private String imageURL;
    private Integer startYear;
    private Integer endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String brandName;
}
