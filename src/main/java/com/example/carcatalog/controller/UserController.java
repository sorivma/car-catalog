package com.example.carcatalog.controller;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new UserDTO());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUserDetails(@PathVariable UUID id,Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-details";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("newUser") @Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(userService.findAll());
            return "users";
        }


        userService.add(userDTO);
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") UUID id, @Valid UserDTO userDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("errors");
            return "users";
        }

        userService.add(userDTO);
        return "users";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, Model model) {
        userService.deactivate(username);
        return "redirect:/users";
    }
}
