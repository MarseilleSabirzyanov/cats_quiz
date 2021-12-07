package com.sabirzyanov.kittens.controller;

import com.sabirzyanov.kittens.DTO.CatDto;
import com.sabirzyanov.kittens.DTO.UserDto;
import com.sabirzyanov.kittens.convertor.UserConvertor;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.service.UserService;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserController {

    final UserService userService;
    final UserConvertor userConvertor;

    @GetMapping
    public String profile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("cats", userService.findCatsByUser(user.getId()));

        return "profile";
    }

    @PostMapping
    public String addCat(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam("catName") @NotBlank String catName,
            @RequestParam("file") @NotBlank MultipartFile file
    ) throws IOException {

        UserDto userDto = userConvertor.convertToUserDto(user);
        userService.addCat(userDto, catName, file);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("cats", userService.findCatsByUser(userDto.getId()));

        return "profile";
    }
}
