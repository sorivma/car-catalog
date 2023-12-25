package com.example.carcatalog.utils.validation.annotation;

import com.example.carcatalog.utils.validation.annotation.validator.UniqueBrandNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueBrandNameValidator.class)
public @interface UniqueBrandName {
    String message() default "Brand name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}