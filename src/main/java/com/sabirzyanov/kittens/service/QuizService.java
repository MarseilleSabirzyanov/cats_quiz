package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.CatsPair;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.domain.UserCatsPair;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.CatsPairRepo;
import com.sabirzyanov.kittens.repository.UserCatsPairRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

@Service
public class QuizService {
    final CatRepo catRepo;
    final UserCatsPairRepo userCatsPairRepo;
    final CatsPairRepo catsPairRepo;

    public QuizService(CatRepo catRepo, UserCatsPairRepo userCatsPairRepo, CatsPairRepo catsPairRepo) {
        this.catRepo = catRepo;
        this.userCatsPairRepo = userCatsPairRepo;
        this.catsPairRepo = catsPairRepo;
    }

    public void startQuiz(Model model, User user) {
        prepareAllPairs();
        prepareUserCatsPair(user);
        List<CatsPair> pairs = catsPairRepo.findAll();
        if (!pairs.isEmpty()) {
            Collections.shuffle(pairs);
            model.addAttribute("leftCat", pairs.get(0).getLeftCat());
            model.addAttribute("rightCat", pairs.get(0).getRightCat());
            model.addAttribute("pair", pairs.get(0));
        } else {
            model.addAttribute("emptyCatListError", "Not enough cats to participate");
        }
    }

    private void prepareUserCatsPair(User user) {
        List<UserCatsPair> allByUser = userCatsPairRepo.findAllByUser(user);
        allByUser.forEach(o -> o.setWinner(null));
        userCatsPairRepo.saveAll(allByUser);

        catsPairRepo.findAll().forEach(pair -> {
            if (userCatsPairRepo.findByUserAndPair(user, pair) == null) {
                userCatsPairRepo.save(new UserCatsPair(user, pair));
            }
        });
    }

    public String setRoundWinner(Cat cat, Model model, User user, CatsPair pair) {
        UserCatsPair userCatsPair = userCatsPairRepo.findByUserAndPair(user, pair);
        if (userCatsPair == null) {
            userCatsPair = new UserCatsPair(user, pair);
        }
        userCatsPair.setWinner(cat);
        userCatsPairRepo.save(userCatsPair);
        
        List<UserCatsPair> userCatsPairWithoutWinner = userCatsPairRepo.findAllByUserAndWinner(user, null);
        if (userCatsPairWithoutWinner.isEmpty()) {
            return "redirect:/top";    
        } else {
            Collections.shuffle(userCatsPairWithoutWinner);
            model.addAttribute("leftCat", userCatsPairWithoutWinner.get(0).getPair().getLeftCat());
            model.addAttribute("rightCat", userCatsPairWithoutWinner.get(0).getPair().getRightCat());    
            model.addAttribute("pair", userCatsPairWithoutWinner.get(0).getPair());    
            
            return "quiz";
        }
    }

    private void prepareAllPairs() {
        List<Cat> cats = catRepo.findAll();
        for (int i = 0; i < cats.size(); i++) {
            for (int j = i+1; j < cats.size(); j++) {
                if  ((catsPairRepo.findByLeftCatAndRightCat(cats.get(i), cats.get(j)) == null) &&
                    (catsPairRepo.findByLeftCatAndRightCat(cats.get(j), cats.get(i)) == null)) {
                    CatsPair pair = new CatsPair(cats.get(i), cats.get(j));
                    catsPairRepo.save(pair);
                }
            }
        }
    }

    public boolean isUserAlreadyPlayed(User user) {
        return userCatsPairRepo.existsByUser(user);
    }
}
