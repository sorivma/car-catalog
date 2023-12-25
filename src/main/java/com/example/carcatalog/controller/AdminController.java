package com.example.carcatalog.controller;

import com.example.carcatalog.dto.BrandDTO;
import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Role;
import com.example.carcatalog.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
@Secured("ADMIN")
public class AdminController {
    private OfferService offerService;
    private BrandService brandService;
    private ModelService modelService;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }
    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }
    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users-table";
    }

    @PostMapping("/toggle-user")
    public String toggleUser(@RequestParam("username") String username) {
        userService.deactivate(username);
        return "redirect:/admin/users";
    }

    @PostMapping("/toggle-role")
    public String toggleAdmin(@RequestParam("username") String username,
                              @RequestParam("role")Role.RoleName roleName) {
        UserDTO userDTO = userService.findByUserName(username);
        userDTO.setRoleName(roleName);
        roleService.assign(userDTO);
        return "redirect:/admin/users";
    }

    @GetMapping("/offers")
    public String showOffers(Model model) {
        model.addAttribute("offers", offerService.findAll());
        return "offers-table";
    }

    @GetMapping("/delete-offer/{id}")
    public String deleteOffer(@PathVariable("id") UUID id) {
        offerService.delete(id);
        return "redirect:/admin/offers";
    }

    @GetMapping("/edit-offer/{id}")
    public String showUpdateOfferForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("offer", offerService.findById(id));
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("models", modelService.findAll());
        return "edit-offer-admin";
    }

    @PostMapping("/edit-offer/{id}")
    public String updateOffer(@PathVariable("id") UUID id, @ModelAttribute("offer") OfferDTO offerDTO) {
        offerDTO.setId(id);
        offerService.update(offerDTO);
        return "redirect:/admin/offers";
    }


    @GetMapping("/brands")
    public String showBrands(Model model) {
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("newBrand", new BrandDTO());
        return "brands-table";
    }

    @PostMapping("/add-brand")
    public String addBrand(@Valid @ModelAttribute("newBrand") BrandDTO brandDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.findAll());
            return "brands-table";
        }
        brandService.add(brandDTO);
        return "redirect:/admin/brands";
    }

    @GetMapping("/models")
    public String showModels(Model model) {
        model.addAttribute("models", modelService.findAll());
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("newModel", new ModelDTO());
        return "models-table";
    }

    @PostMapping("/add-model")
    public String addModel(@Valid @ModelAttribute("newModel") ModelDTO modelDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.findAll());
            model.addAttribute("models", modelService.findAll());
            return "models-table";
        }

        modelService.add(modelDTO);
        return "redirect:/admin/models";
    }

}
