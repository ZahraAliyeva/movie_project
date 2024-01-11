package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language")
    private String language;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmName> filmName;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmLanguage> films;
}