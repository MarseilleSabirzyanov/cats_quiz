package com.sabirzyanov.kittens.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private Set<UserCatRating> userCatRating;

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
