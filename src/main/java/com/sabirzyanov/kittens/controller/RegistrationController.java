package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            Model model
    ) {
        if (!userService.addUser(user, model)) {
            return "registration";
        }

        model.addAttribute("registrationSuccess", "Вы успешно зарегистрированы!");

        return "redirect:/login";
    }

}
