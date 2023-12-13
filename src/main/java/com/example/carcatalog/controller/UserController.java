package com.example.carcatalog.controller;

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


import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
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
        LOG.log(Level.INFO, "Show all users");
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new UserDTO());
        return "users";
    }

    @GetMapping("/{username}")
    public String showUserDetails(@PathVariable String username,Model model) {
        LOG.log(Level.INFO, "Show details for user: " + username);
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("offers", offerService.getUserOffers(username));
        return "user-details";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("newUser") @Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users",userService.findAll());
            return "users";
        }

        LOG.log(Level.INFO, "Add user with credentials: " + userDTO);
        userService.add(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/edit/{username}")
    public String showUpdateForm(@PathVariable("username") String username, Model model) {
        UserDTO userDTO = userService.findByUserName(username);
        model.addAttribute("user", userDTO);
        return "update-user";
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, @Valid UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            userDTO.setUsername(username);
            return "update-user";
        }

        userService.update(userDTO);
        return "redirect:/users";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, Model model) {
        userService.deactivate(username);
        return "redirect:/users";
    }
}
