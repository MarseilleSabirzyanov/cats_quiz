package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    final CatRepo catRepo;
    final UserRepo userRepo;

    public MainController(CatRepo catRepo, UserRepo userRepo) {
        this.catRepo = catRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
}
