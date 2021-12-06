package com.sabirzyanov.kittens.repository;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.CatsPair;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.domain.UserCatsPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCatsPairRepo extends JpaRepository<UserCatsPair, Long> {
    UserCatsPair findByUserAndPair(User user, CatsPair pair);

    List<UserCatsPair> findAllByUser(User user);

    List<UserCatsPair> findAllByUserAndWinner(User user, Cat cat);

    @Query("SELECT new com.sabirzyanov.kittens.repository.TotalRating(c.id, c.name, c.filename, COUNT(ucp.winner)) " +
            "FROM UserCatsPair ucp join ucp.winner c WHERE ucp.user = ?1 GROUP BY c.id, c.name ORDER BY count(ucp.winner) DESC")
    List<TotalRating> sumUserRating(User user);

    @Query("SELECT new com.sabirzyanov.kittens.repository.TotalRating(c.id, c.name, c.filename, COUNT(ucp.winner)) " +
            "FROM UserCatsPair ucp join ucp.winner c GROUP BY c.id, c.name ORDER BY count(ucp.winner) DESC")
    List<TotalRating> sumTotalRating();

    boolean existsByUser(User user);
}
