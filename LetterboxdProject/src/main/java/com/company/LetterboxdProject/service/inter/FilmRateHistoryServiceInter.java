package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.FilmRateHistoryRequest;


public interface FilmRateHistoryServiceInter {

    Integer filmRating(final Long filmId);
    void addLikedFilm(final Long userId, final Long filmId);
    void deleteLikedFilm(final Long userId, final Long filmId);
    void addWatchedFilm(final Long userId, final Long filmId);
    void deleteWatchedFilm(final Long userId, final Long filmId);
    void giveRate(final Long userId, final Long filmId, final FilmRateHistoryRequest rate);
}
