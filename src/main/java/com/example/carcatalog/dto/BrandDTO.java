package com.example.carcatalog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * DTO for the {@link com.example.carcatalog.entity.Brand} entity.
 * Used for the view layer.
 * Contains the name field.
 * Extends {@link BaseDTO}.
 * <p>
 *     The annotations are used by the validator.
 *     <br>
 *     The annotations are:
 *     <ul>
 *         <li>{@link NotEmpty} - the field must not be empty</li>
 *     </ul>
 *     <br>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BrandDTO extends BaseDTO{
    @NotEmpty
    private String name;
}
