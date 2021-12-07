package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.repository.TotalRating;
import com.sabirzyanov.kittens.repository.UserCatsPairRepo;
import com.sabirzyanov.kittens.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final UserCatsPairRepo userCatsPairRepo;
    private final UserRepo userRepo;

    public List<TotalRating> getUserRating(Long userId) {
        return userCatsPairRepo.sumUserRating(userRepo.findById(userId).get());
    }

    public List<TotalRating> getTotalRating() {
        return userCatsPairRepo.sumTotalRating();
    }
}
