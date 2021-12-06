package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.TotalRating;
import com.sabirzyanov.kittens.repository.UserCatsPairRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final CatRepo catRepo;
    private final UserCatsPairRepo userCatsPairRepo;

    public RatingService(CatRepo catRepo, UserCatsPairRepo userCatsPairRepo) {
        this.catRepo = catRepo;
        this.userCatsPairRepo = userCatsPairRepo;
    }

    public List<TotalRating> getUserRating(User user) {
        return userCatsPairRepo.sumUserRating(user);
    }

    public List<TotalRating> getTotalRating() {
        return userCatsPairRepo.sumTotalRating();
    }
}
