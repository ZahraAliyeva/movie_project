package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmName> filmName;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmCastAndCrew> filmCastAndCrews;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmWatchlist> filmWatchlists;

    @Column(name = "plot")
    private String plot;

    @Column(name = "running_time")
    private Integer runningTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_links", referencedColumnName = "id")
    private FilmLinks filmLinks;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Poster> posters;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmRateHistory> filmRateHistories;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Review> reviews;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Release> releases;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmLanguage> languages;

    @ManyToMany(mappedBy = "films")
    private Set<FilmList> list;

    @ManyToMany(mappedBy = "films")
    private Set<FavoriteFilm> favoriteFilms;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_studios",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id", referencedColumnName = "id"))
    private Set<Studio> studios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_countries",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Set<Country> countries;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "films_themes",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"))
    private Set<Theme> themes;
}