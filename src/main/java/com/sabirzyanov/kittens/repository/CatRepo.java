package com.sabirzyanov.kittens.repository;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepo extends JpaRepository<Cat, Long> {
    List<Cat> findAllByUser(User user);
}
