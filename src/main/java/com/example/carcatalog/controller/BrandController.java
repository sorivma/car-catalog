package com.example.carcatalog.controller;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String showBrandCatalog() {
        return "brand-catalog";
    }

    @PostMapping("/add")
    public String addBrand(@Validated BrandDTO brandDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "brand-catalog";
        }

        brandService.add(brandDTO);
        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String deleteBrand(@PathVariable UUID id) {
        brandService.delete(id);
        return "redirect:/index";
    }
}
