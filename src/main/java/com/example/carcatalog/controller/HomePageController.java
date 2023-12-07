package com.example.carcatalog.controller;

import com.example.carcatalog.service.BrandService;
import com.example.carcatalog.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home page controller
 * <p>
 *     This class is used to handle requests for the home page.
 *     <br>
 *     The requests are:
 *     <ul>
 *         GET / - returns the home page
 *     </ul>
 */
@Controller
public class HomePageController {
    private OfferService offerService;
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * Returns the home page.
     * <p>
     *     This method is used to handle GET requests for the home page.
     *     <br>
     *     The method returns the home page.
     *     <br>
     *     The method uses the {@link OfferService} to get all offers.
     *     <br>
     * @param model the model
     * @return the home page
     */
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("offers", offerService.findAll());
        return "home";
    }

    @GetMapping("/brands")
    public String brands(Model model){
        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }
}
