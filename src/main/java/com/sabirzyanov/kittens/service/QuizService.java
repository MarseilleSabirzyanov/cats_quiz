package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.domain.UserCatRating;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.UserCatRatingRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private HashMap<Cat, Integer> catPoints;
    private List<List<Cat>> catsPair;
    private int pairNumber;

    final CatRepo catRepo;
    final UserCatRatingRepo userCatRatingRepo;

    public QuizService(CatRepo catRepo, UserCatRatingRepo userCatRatingRepo) {
        this.catRepo = catRepo;
        this.userCatRatingRepo = userCatRatingRepo;
    }

    public void startQuiz(Model model) {
        List<Cat> cats = catRepo.findAll();
        catsPair = new ArrayList<>();
        catPoints = new HashMap<>();

        for (int i = 0; i < cats.size(); i++) {
            catPoints.put(cats.get(i), 0);
            for (int j = i+1; j < cats.size(); j++) {
                ArrayList<Cat> pair = new ArrayList<>();
                pair.add(cats.get(i));
                pair.add(cats.get(j));
                catsPair.add(pair);
            }
        }

        Collections.shuffle(catsPair);
        pairNumber = 0;
        model.addAttribute("leftCat", catsPair.get(pairNumber).get(0));
        model.addAttribute("rightCat", catsPair.get(pairNumber).get(1));
    }

    public String setRoundWinner(Cat cat, Model model, User user) {
        catPoints.put(cat, catPoints.get(cat)+1);
        pairNumber++;
        if (pairNumber < catsPair.size()) {
            model.addAttribute("leftCat", catsPair.get(pairNumber).get(0));
            model.addAttribute("rightCat", catsPair.get(pairNumber).get(1));
        } else {
            HashMap<Cat, Integer> sortedCatPoints = sortByValue(catPoints);
            setUserCatRating(sortedCatPoints, user);
            return "redirect:/top";
        }

        return "quiz";
    }

    private HashMap<Cat, Integer> getTotalRating() {
        Iterable<UserCatRating> userCatRatings = userCatRatingRepo.findAll();
        HashMap<Cat, Integer> catRating = new HashMap<>();
        userCatRatings.forEach(u -> catRating.put(
                u.getCat(),
                catRating.getOrDefault(u.getCat(), 0) + u.getRating()
        ));
        return catRating;
    }

    private HashMap<Cat, Integer> getUserRating(User user) {
        Iterable<UserCatRating> userCatRatings = userCatRatingRepo.findAllByUserOrderByRating(user);
        HashMap<Cat, Integer> catRating = new HashMap<>();
        userCatRatings.forEach(u -> catRating.put(u.getCat(), u.getRating()));
        return catRating;
    }

    private void setUserCatRating(HashMap<Cat, Integer> catPoints, User user) {
        catPoints.forEach((cat, point) -> {
            UserCatRating userCatRating = userCatRatingRepo.findByUserAndCat(user, cat).orElse(new UserCatRating(user, cat));
            userCatRating.setRating(point);
            userCatRatingRepo.save(userCatRating);
        });
    }

    public static HashMap<Cat, Integer> sortByValue(HashMap<Cat, Integer> hm)
    {
        HashMap<Cat, Integer> temp
                = hm.entrySet()
                .stream()
                .sorted(Map.Entry.<Cat, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return temp;
    }

    public boolean isUserAlreadyPlayed(User user) {
        return userCatRatingRepo.existsByUser(user);
    }
}
