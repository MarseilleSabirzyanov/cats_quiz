package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            BindingResult bindingResult,
                            Model model
    ) {
        User user = userService.findUser(username);

        if (user == null) {
            model.addAttribute("loginError", "логин или пароль неверны");
            return "login";
        } else if(!user.getPassword().equals(userService.encodedPassword(password))) {
            model.addAttribute("loginError", "логин или пароль неверны");
            return "login";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "login";
        }
        return "redirect:/profile";
    }

}
