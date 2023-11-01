package com.example.carcatalog.controller;

import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.entity.Model;
import com.example.carcatalog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Validated UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.add(userDTO);
        return "redirect:/index";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") UUID id, @Validated UserDTO userDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-user";
        }

        userService.add(userDTO);
        return "redirect:/index";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, Model model) {
        userService.deactivate(username);
        return "redirect:/index";
    }
}
