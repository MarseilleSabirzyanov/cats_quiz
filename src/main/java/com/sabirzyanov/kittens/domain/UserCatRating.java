package com.sabirzyanov.kittens.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_cat_rating")
public class UserCatRating extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    private int rating;

    public UserCatRating(User user, Cat cat) {
        this.user = user;
        this.cat = cat;
    }
}
