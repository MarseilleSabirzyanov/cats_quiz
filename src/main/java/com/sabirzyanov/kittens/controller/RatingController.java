package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.RatingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/top")
public class RatingController {
    final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public String rating(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userRating", ratingService.getUserRating(user.getId()));
        model.addAttribute("totalRating", ratingService.getTotalRating());

        return "top";
    }
}
