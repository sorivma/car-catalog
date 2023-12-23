package com.example.carcatalog.controller;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.viewmodel.OfferViewModel;
import com.example.carcatalog.except.ClientErrorException;
import com.example.carcatalog.service.ModelService;
import com.example.carcatalog.service.OfferService;
import com.example.carcatalog.service.UserService;
import com.example.carcatalog.service.impl.RecentOfferService;
import lombok.extern.java.Log;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Offer controller
 * <p>
 * This class is used to handle requests for offers.
 * <br>
 * The requests are:
 * <ul>
 *     GET /offers/{id} - returns the offer page
 */
@Controller
@RequestMapping("/offers")
public class OfferController {
    private RecentOfferService recentOfferService;
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

    @Autowired
    public void setRecentOfferService(RecentOfferService recentOfferService) {
        this.recentOfferService = recentOfferService;
    }

    @GetMapping("/{id}")
    public String getModelOffers(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("offers", offerService.getModelOffers(id));
        return "home";
    }

    /**
     * Returns the offer page.
     * <p>
     * This method is used to handle GET requests for the offer page.
     * <br>
     * The method returns the offer page.
     * <br>
     * The method uses the {@link OfferService} to get the offer view model.
     *
     * @param offerId the offer id
     * @param model   the model
     * @return the offer page
     */
    @GetMapping("/details/{id}")
    public String offerPage(@PathVariable("id") UUID offerId, Model model, Principal principal) {
        OfferViewModel offerViewModel = offerService.getOfferViewModel(offerId);
        model.addAttribute("offerModel",
                offerViewModel);
        if (principal != null) {
            recentOfferService.addRecent(offerViewModel.getOfferDTO(), principal.getName());
        }
        return "offer-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable("id") UUID offerId, Principal principal) {

        if (offerService.findById(offerId).getSellerUsername().equals(principal.getName())) {
            offerService.delete(offerId);
            return "redirect:/users/" + principal.getName();
        }

        throw new ClientErrorException.ProhibitedActionException("delete", "offer");
    }

    @GetMapping("/recent")
    public String getRecent(Model model, Principal principal) {
        model.addAttribute("offers", recentOfferService.getRecent(principal.getName()));
        return "home";
    }

    @GetMapping("/create-offer")
    public String showCreateOfferForm(Model model) {
        List<ModelDTO> availableModels = modelService.findAll();
        model.addAttribute("availableModels", availableModels);

        model.addAttribute("offer", new OfferDTO());
        return "create-offer";
    }

    @PostMapping("/create-offer")
    public String processCreateOfferForm(@ModelAttribute("offer") OfferDTO offer,
                                         BindingResult bindingResult,
                                         Principal principal) {
        if (bindingResult.hasErrors()) {
            return "create-offer";
        }

        offer.setSellerUsername(principal.getName());
        offerService.add(offer);
        return "redirect:/users/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String showEditOfferForm(@PathVariable("id") UUID offerId, Model model) {
        OfferDTO offerDTO = offerService.findById(offerId);

        List<ModelDTO> availableModels = modelService.findAll();
        model.addAttribute("availableModels", availableModels);

        model.addAttribute("offer", offerDTO);
        return "edit-offer";
    }

    @PostMapping("/edit/{id}")
    public String processEditOfferForm(@PathVariable("id") UUID offerId,
                                       @ModelAttribute("offer") OfferDTO offer,
                                       BindingResult bindingResult,
                                       Principal principal) {
        if (!offerService.findById(offerId).getSellerUsername().equals(principal.getName())) {
            throw new ClientErrorException.ProhibitedActionException("edit", "offer");
        }

        if (bindingResult.hasErrors()) {
            return "edit-offer";
        }

        offer.setSellerUsername(principal.getName());
        offer.setId(offerId);
        offerService.update(offer);
        return "redirect:/users/dashboard";
    }
}
