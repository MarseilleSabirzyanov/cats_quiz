package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.DTO.CatDto;
import com.sabirzyanov.kittens.convertor.CatConvertor;
import com.sabirzyanov.kittens.convertor.UserConvertor;
import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    final QuizService quizService;
    final UserConvertor userConvertor;
    final CatConvertor catConvertor;

    @GetMapping
    public String quiz(@AuthenticationPrincipal User user, Model model) {
        if (quizService.isUserAlreadyPlayed(user.getId())) {
            model.addAttribute("startButton", "Перепройти");
        } else
            model.addAttribute("startButton", "Старт");

        return "quiz";
    }

    @PostMapping(params = "quiz")
    public String startQuiz(@AuthenticationPrincipal User user, Model model) {
        quizService.startQuiz(model, userConvertor.convertToUserDto(user));

        return "quiz";
    }

    @PostMapping(params = "leftCat")
    public String leftCat(
            @AuthenticationPrincipal User user,
            @RequestParam("leftCatId") Cat cat,
            @RequestParam("pairId") Long pairId,
            Model model
    ) {
        return quizService.setRoundWinner(catConvertor.convertToCatDto(cat), model, userConvertor.convertToUserDto(user), pairId);
    }

    @PostMapping(params = "rightCat")
    public String rightCat(
            @AuthenticationPrincipal User user,
            @RequestParam("rightCatId") Cat cat,
            @RequestParam("pairId") Long pairId,
            Model model
    ) {
        return quizService.setRoundWinner(catConvertor.convertToCatDto(cat), model, userConvertor.convertToUserDto(user), pairId);
    }
}
