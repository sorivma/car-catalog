package com.example.carcatalog.controller;

import com.example.carcatalog.service.BrandService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(BrandController.class);
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String brands(Model model){
        LOG.log(Level.INFO, "Get all brands");
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }
}
