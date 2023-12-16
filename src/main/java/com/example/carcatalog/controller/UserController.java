package com.example.carcatalog.controller;

import com.example.carcatalog.dto.RegistrationDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.service.OfferService;
import com.example.carcatalog.service.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private OfferService offerService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOfferService(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new UserDTO());
        return "users";
    }

    @GetMapping("/{username}")
    public String showUserDetails(@PathVariable String username,Model model) {
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("offers", offerService.getUserOffers(username));
        return "user-details";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        UserDTO userDTO = userService.findByUserName(principal.getName());
        model.addAttribute("user", userDTO);
        return "dashboard";
    }

    @GetMapping("/registration")
    public String showRegForm(Model model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.register(registrationDTO);

        return "redirect:/login";
    }
}
