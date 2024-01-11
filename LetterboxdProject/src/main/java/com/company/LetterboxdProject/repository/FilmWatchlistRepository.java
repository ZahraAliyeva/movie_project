package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmWatchlist;
import com.company.LetterboxdProject.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmWatchlistRepository extends JpaRepository<FilmWatchlist, Long> {

    FilmWatchlist findByWatchlistAndFilm(final Watchlist watchlist, final Film film);
}
