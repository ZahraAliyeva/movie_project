package com.company.LetterboxdProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "watchlists")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<FilmWatchlist> filmWatchlists;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "films_watchlists",
//            joinColumns = @JoinColumn(name = "watchlists_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
//    private Set<Film> films;

    public Watchlist(User user) {
        this.user = user;
    }
}
