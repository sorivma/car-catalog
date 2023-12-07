package com.example.carcatalog.controller;

import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.OfferService;
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
    private OfferService offerService;
    private ModelService modelService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/{id}")
    public String getModelOffers(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("offers", offerService.getModelOffers(id));
        return "home";
    }

    @GetMapping("/all")
    public String brands(Model model){
        model.addAttribute("models", modelService.findAll());
        return "models";
    }
}
