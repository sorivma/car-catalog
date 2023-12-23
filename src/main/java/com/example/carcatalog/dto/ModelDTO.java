package com.example.carcatalog.dto;

import com.example.carcatalog.entity.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * DTO for the {@link com.example.carcatalog.entity.Model} entity.
 * Used for the view layer.
 * Contains the name, category, imageURL, startYear, endYear, brandName fields.
 * Extends {@link BaseDTO}.
 * <p>
 *     The annotations are used by the validator.
 *     <br>
 *     The annotations are:
 *     <ul>
 *         <li>{@link NotEmpty} - the field must not be empty</li>
 *         <li>{@link Enumerated} - the field must be one of the enum values</li>
 *     </ul>
 *     <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ModelDTO extends BaseDTO{
    @NotBlank
    private String name;
    @Enumerated(EnumType.STRING)
    private Model.Category category;
    private String imageURL;
    private Integer startYear;
    private Integer endYear;
    @NotEmpty
    private String brandName;
}
