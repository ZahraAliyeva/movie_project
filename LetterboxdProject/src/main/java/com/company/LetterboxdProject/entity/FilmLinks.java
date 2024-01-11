package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "film_links")
@RequiredArgsConstructor
public class FilmLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tmdb_link")
    private String tmdbLink;

    @Column(name = "imdb_link")
    private String imdbLink;

    @Column(name = "trailer")
    private String trailer;

    @OneToOne(mappedBy = "filmLinks")
    @JsonIgnore
    private Film film;

    public FilmLinks(String tmdbLink, String imdbLink, String trailer) {
        this.tmdbLink = tmdbLink;
        this.imdbLink = imdbLink;
        this.trailer = trailer;
    }
}
