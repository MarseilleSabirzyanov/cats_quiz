package com.sabirzyanov.kittens.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_cats_pair")
public class UserCatsPair extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pair_id")
    CatsPair pair;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "winner_cat_id")
    Cat winner;

    public UserCatsPair(User user, CatsPair pair) {
        this.user = user;
        this.pair = pair;
    }

    public UserCatsPair(User user, CatsPair pair, Cat winner) {
        this.user = user;
        this.pair = pair;
        this.winner = winner;
    }
}
