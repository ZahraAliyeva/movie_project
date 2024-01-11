package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmRateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface FilmRateHistoryRepository extends JpaRepository<FilmRateHistory, Long>{

    @Query("select rh.film from FilmRateHistory rh where rh.user.id = ?1 order by rh.likeDate")
    Set<Film> findTop4ByOrderByLikedDateDesc(final Long userId);
    @Query("select rh from FilmRateHistory rh where rh.user.id = ?1 and rh.film.id = ?2")
    FilmRateHistory findByUserAndFilm(final Long userId, final Long filmId);
    @Query("select rh.ratePoint.rate from FilmRateHistory rh where rh.film.id = ?1")
    Set<Integer> findFilmRatePoint(Long FilmId);
    @Query("Select rh.ratePoint.rate FROM FilmRateHistory rh where rh.user.id = ?1 and rh.film.id = ?2")
    Double findFilmRate(final Long userId, final Long filmId);
    @Query("Select rh.watchedDate FROM FilmRateHistory rh where rh.user.id = ?1 and rh.film.id = ?2")
    LocalDate findWatchedDate(final Long userId, final Long filmId);
    @Query("select count(rh) from FilmRateHistory rh where rh.film.id = ?1 and rh.watched = true ")
    Long findByWatched(final Long filmId);
    @Query("select count(rh) from FilmRateHistory rh where rh.film.id = ?1 and rh.likes = true ")
    Long findByLikes(final Long filmId);

    @Query("select rh.film from FilmRateHistory rh where rh.user.id = ?1 and rh.watched = true")
    Set<Film> findFilmsByWatched(final Long userId);

    @Query("select rh.film from FilmRateHistory rh where rh.user.id = ?1 and rh.likes = true")
    Set<Film> findFilmsByLiked(final Long userId);
}
