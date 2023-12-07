package com.example.carcatalog.controller;

import com.example.carcatalog.service.OfferService;
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
    protected OfferService offerService;


    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
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
    @GetMapping("/{id}")
    public String offerPage(@PathVariable("id") UUID offerId, Model model) {
        model.addAttribute("offerModel",
                offerService.getOfferViewModel(offerId));

        return "offer-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID offerId, Model model) {
        offerService.delete(offerId);
        return "redirect:/";
    }
}
