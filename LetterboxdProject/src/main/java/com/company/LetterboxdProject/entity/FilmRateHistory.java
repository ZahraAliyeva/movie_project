package com.company.LetterboxdProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "film_rate_history")
public class FilmRateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_likes")
    private Boolean likes;

    @Column(name = "like_date")
    private LocalDate likeDate;

    @Column(name = "watched")
    private Boolean watched;

    @Column(name = "watched_date")
    private LocalDate watchedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "rate_point_id")
    private RatePoint ratePoint;

    @Column(name = "rate_date")
    private LocalDate rateDate;
}