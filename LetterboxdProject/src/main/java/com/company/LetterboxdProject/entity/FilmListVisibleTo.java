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
@Table(name = "film_list_visible_to")
public class FilmListVisibleTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "options")
    private String option;

    @OneToMany(mappedBy = "filmListVisibleTo", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<FilmList> filmLists;
}
