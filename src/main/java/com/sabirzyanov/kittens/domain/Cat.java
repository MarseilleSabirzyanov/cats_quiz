package com.sabirzyanov.kittens.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cat")
public class Cat extends BaseEntity {
    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "file_name")
    private String filename;

    @OneToMany(mappedBy = "leftCat", cascade = CascadeType.ALL)
    private List<CatsPair> leftCatsPairList;
    @OneToMany(mappedBy = "rightCat", cascade = CascadeType.ALL)
    private List<CatsPair> rightCatsPairList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    public Cat(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Cat(String name, User user, String filename) {
        this.name = name;
        this.user = user;
        this.filename = filename;
    }
}
