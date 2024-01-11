package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_pronoun")
public class UserPronoun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pronoun")
    private String pronoun;

    @OneToMany(mappedBy = "pronoun")
    @JsonIgnore
    private Set<User> users;
}
