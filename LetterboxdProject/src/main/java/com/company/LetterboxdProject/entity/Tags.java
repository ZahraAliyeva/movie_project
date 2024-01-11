package com.company.LetterboxdProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tags")
    private String tags;

    @ManyToMany(mappedBy = "tags")
    private Set<FilmList> lists;

    @ManyToMany(mappedBy = "tags")
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "tags")
    private Set<ReviewComment> comments;
}
