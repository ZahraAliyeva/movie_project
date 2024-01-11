package com.company.LetterboxdProject.service.inter;

public interface WatchListService {

    String addFilmToWatchlist(final Long userId, final Long filmId);
    String deleteFilmFromWatchlist(final Long userId, final Long filmId);
}
