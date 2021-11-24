package com.sabirzyanov.kittens.repository;

import com.sabirzyanov.kittens.domain.Cat;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.domain.UserCatRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCatRatingRepo extends CrudRepository<UserCatRating, Long> {
    Optional<UserCatRating> findByUserAndCat(User user, Cat cat);

    Iterable<UserCatRating> findAllByUserOrderByRating(User user);

    //TODO join fetch wont work
    @Query("SELECT new com.sabirzyanov.kittens.repository.TotalRating(c.id, c.name, c.filename, SUM(ucr.rating)) " +
            "FROM UserCatRating ucr join ucr.cat c GROUP BY c.id, c.name ORDER BY SUM(ucr.rating) DESC")
    List<TotalRating> sumTotalRating();

    @Query("SELECT new com.sabirzyanov.kittens.repository.TotalRating(c.id, c.name, c.filename, SUM(ucr.rating)) " +
            "FROM UserCatRating ucr join ucr.cat c WHERE ucr.user = ?1 GROUP BY c.id, c.name ORDER BY SUM(ucr.rating) DESC")
    List<TotalRating> sumUserRating(User user);

    boolean existsByUser(User user);
}
