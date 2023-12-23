package com.example.carcatalog.controller;

import com.example.carcatalog.dto.RegistrationDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.dto.form.PasswordUpdateForm;
import com.example.carcatalog.dto.form.UsernameUpdateForm;
import com.example.carcatalog.service.OfferService;
import com.example.carcatalog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

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
        return "users-table";
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

    @GetMapping("/edit-profile")
    public String showEditProfile(Model model, Principal principal) {
        UserDTO userDTO = userService.findByUserName(principal.getName());
        model.addAttribute("user", userDTO);
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String editProfile(@Valid @ModelAttribute("user") UserDTO userDTO,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-profile";
        }

        userService.update(userDTO);

        return "redirect:/users/dashboard";
    }

    @GetMapping("/edit-password")
    public String showEditPassword(Model model) {
        model.addAttribute("passwordUpdateForm", new PasswordUpdateForm());
        return "edit-password";
    }

    @PostMapping("/edit-password")
    public String editPassword(@Valid @ModelAttribute("passwordUpdateForm" ) PasswordUpdateForm passwordUpdateForm,
                              Principal principal,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-password";
        }

        userService.updatePassword(principal.getName(), passwordUpdateForm.getOldPassword(), passwordUpdateForm.getNewPassword());

        return "redirect:/users/dashboard";
    }

    @GetMapping("/edit-username")
    public String showEditUsername(Model model) {
        model.addAttribute("newUsername", new UsernameUpdateForm());
        return "edit-username";
    }

    @PostMapping("/edit-username")
    public String editUsername(@Valid @ModelAttribute("newUsername") UsernameUpdateForm username,
                                 BindingResult bindingResult,
                              Principal principal) {

        if (bindingResult.hasErrors()) {
            return "edit-username";
        }

        userService.updateUsername(principal.getName(), username.getUsername());


        return "redirect:/logout";
    }
}
