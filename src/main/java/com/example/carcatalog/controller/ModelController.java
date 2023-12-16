package com.example.carcatalog.controller;

import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.OfferService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/models")
public class ModelController {
    private ModelService modelService;

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/brand/{brand_name}")
    public String getBrandModels(@PathVariable("brand_name") String brandName, Model model) {
        model.addAttribute("models", modelService.getBrandModels(brandName));
        return "models";
    }
    @GetMapping("/all")
    public String models(Model model){
        model.addAttribute("models", modelService.findAll());
        return "models";
    }
}
