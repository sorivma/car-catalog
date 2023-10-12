package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Brand;
import com.example.carcatalog.entity.Model;
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
public class ModelDTO {
    private UUID id;
    private String name;
    private Model.Category category;
    private String imageURL;
    private LocalDateTime startYear;
    private LocalDateTime endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Brand brand;
}
