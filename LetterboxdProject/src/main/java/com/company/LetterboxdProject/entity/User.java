package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "location")
    private String location;

    @Column(name = "bio")
    private String bio;

    @Column(name = "website")
    private String website;

    @Column(name = "custom_poster")
    private Boolean customPoster;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "update_date")
    private LocalDate updatedDate;

    @Column(name = "poster_shown")
    private Boolean posterShown;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserPhoto userPhoto;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private UserCredential userCredentials;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Watchlist watchlist;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private FavoriteFilm favoriteFilm;


    @ManyToOne()
    @JoinColumn(name = "pronoun_id")
    private UserPronoun pronoun;

    @ManyToOne()
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmRateHistory> filmRateHistories;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<FilmList> filmLists;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ReviewComment> comments;

    @OneToMany(mappedBy="to")
    private Set<Follower> followers;

    @OneToMany(mappedBy="from")
    private Set<Follower> following;
}
