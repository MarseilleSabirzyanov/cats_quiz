package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.repository.CatRepo;
import com.sabirzyanov.kittens.repository.TotalRating;
import com.sabirzyanov.kittens.repository.UserCatRatingRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final CatRepo catRepo;
    private final UserCatRatingRepo userCatRatingRepo;

    public RatingService(CatRepo catRepo, UserCatRatingRepo userCatRatingRepo) {
        this.catRepo = catRepo;
        this.userCatRatingRepo = userCatRatingRepo;
    }

    public List<TotalRating> findRatingList() {
        return userCatRatingRepo.sumTotalRating();
    }

    public List<TotalRating> getUserRating(User user) {
        return userCatRatingRepo.sumUserRating(user);
    }

    public List<TotalRating> getTotalRating() {
        return userCatRatingRepo.sumTotalRating();
    }
}
