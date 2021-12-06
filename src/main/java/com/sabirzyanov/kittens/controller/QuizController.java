package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.CatsPair;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.QuizService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public String quiz(@AuthenticationPrincipal User user, Model model) {
        if (quizService.isUserAlreadyPlayed(user)) {
            model.addAttribute("startButton", "Перепройти");
        } else
            model.addAttribute("startButton", "Старт");

        return "quiz";
    }

    @PostMapping(params = "quiz")
    public String startQuiz(@AuthenticationPrincipal User user, Model model) {
        quizService.startQuiz(model, user);

        return "quiz";
    }

    @PostMapping(params = "leftCat")
    public String leftCat(
            @AuthenticationPrincipal User user,
            @RequestParam("leftCatId") Cat cat,
            @RequestParam("pairId") CatsPair pair,
            Model model
    ) {
        return quizService.setRoundWinner(cat, model, user, pair);
    }

    @PostMapping(params = "rightCat")
    public String rightCat(
            @AuthenticationPrincipal User user,
            @RequestParam("rightCatId") Cat cat,
            @RequestParam("pairId") CatsPair pair,
            Model model
    ) {
        return quizService.setRoundWinner(cat, model, user, pair);
    }
}
