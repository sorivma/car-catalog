package com.example.carcatalog.controller;

import com.example.carcatalog.service.OfferService;
import lombok.extern.java.Log;
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

/**
 * Offer controller
 * <p>
 *     This class is used to handle requests for offers.
 *     <br>
 *     The requests are:
 *     <ul>
 *         GET /offers/{id} - returns the offer page
 */
@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(OfferController.class);
    private OfferService offerService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public String getModelOffers(@PathVariable("id") UUID id, Model model) {
        LOG.log(Level.INFO, "Get offers for model under id: " + id);
        model.addAttribute("offers", offerService.getModelOffers(id));
        return "home";
    }

    /**
     * Returns the offer page.
     * <p>
     *     This method is used to handle GET requests for the offer page.
     *     <br>
     *     The method returns the offer page.
     *     <br>
     *     The method uses the {@link OfferService} to get the offer view model.
     * @param offerId the offer id
     * @param model the model
     * @return the offer page
     */
    @GetMapping("/details/{id}")
    public String offerPage(@PathVariable("id") UUID offerId, Model model) {
        LOG.log(Level.INFO, "Get details for offer under id: " + offerId);
        model.addAttribute("offerModel",
                offerService.getOfferViewModel(offerId));

        return "offer-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID offerId, Model model) {
        LOG.log(Level.INFO, "Delete offer under id: " + offerId);
        offerService.delete(offerId);
        return "redirect:/";
    }
}
