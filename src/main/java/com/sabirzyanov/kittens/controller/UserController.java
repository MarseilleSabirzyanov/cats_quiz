package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("cats", userService.findCatsByUser(user));

        return "profile";
    }

    @PostMapping
    public String addCat(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam("catName") @NotBlank String catName,
            @RequestParam("file") @NotBlank MultipartFile file
    ) throws IOException {

        userService.addCat(user, catName, file);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("cats", userService.findCatsByUser(user));

        return "profile";
    }
}
