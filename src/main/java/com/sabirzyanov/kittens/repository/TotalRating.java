package com.sabirzyanov.kittens.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalRating {
    Long id;
    String name;
    Long rating;
    String filename;

    public TotalRating(Long id, String name, String filename, Long rating) {
        this.id = id;
        this.name = name;
        this.filename = filename;
        this.rating = rating;
    }

}
