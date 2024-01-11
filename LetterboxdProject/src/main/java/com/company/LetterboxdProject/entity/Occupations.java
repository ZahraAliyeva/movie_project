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
@Table(name = "occupations")
public class Occupations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "occupations")
    private String occupation;

    @OneToMany(mappedBy = "occupations")
    @JsonIgnore
    private Set<FilmCastAndCrew> filmCastAndCrews;
}