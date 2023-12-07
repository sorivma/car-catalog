package com.example.carcatalog.controller;

import com.example.carcatalog.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/models/{name}")
    public String brandModels(@PathVariable("name") String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("models", brandService.findModelsByName(name));
        return "models";
    }
}
