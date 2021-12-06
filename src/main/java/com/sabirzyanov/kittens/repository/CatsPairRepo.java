package com.sabirzyanov.kittens.repository;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.CatsPair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatsPairRepo extends JpaRepository<CatsPair, Long> {
    CatsPair findByLeftCatAndRightCat(Cat leftCat, Cat rightCat);
}
