package com.example.carcatalog.utils.validation.annotation.validator;

import com.example.carcatalog.service.BrandService;
import com.example.carcatalog.utils.validation.annotation.UniqueBrandName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class UniqueBrandNameValidator implements ConstraintValidator<UniqueBrandName, String> {

    // Inject your brand service here
    private final BrandService brandService;

    public UniqueBrandNameValidator(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public void initialize(UniqueBrandName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String brandName, ConstraintValidatorContext context) {
        // Check if the brand name is unique using your service
        return brandName != null && !brandService.existsByName(brandName);
    }
}