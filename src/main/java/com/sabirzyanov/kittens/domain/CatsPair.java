package com.sabirzyanov.kittens.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cats_pair")
public class CatsPair extends BaseEntity{
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "left_cat_id", nullable = false)
    private Cat leftCat;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "right_cat_id", nullable = false)
    private Cat rightCat;

    public CatsPair(Cat leftCat, Cat rightCat) {
        this.leftCat = leftCat;
        this.rightCat = rightCat;
    }
}
