package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.FilmFilterRequest;
import com.company.LetterboxdProject.dto.FilmRequest;
import com.company.LetterboxdProject.dto.FilmResponse;

import java.util.List;
import java.util.Set;

public interface FilmServiceInter {

    FilmResponse saveFilm(final FilmRequest filmRequest);
    FilmResponse edit(final Long id, final FilmRequest filmRequest);
    void deleteFilm(final Long id);
    List<FilmResponse> getFilms();
    FilmResponse getFilmById(final Long id);
    List<FilmResponse> getWatchList(final Long userId);
    List<FilmResponse> getFilmFromList( final Long listId);
    Set<FilmResponse> watchedFilmsByUser(final Long userId);
    Set<FilmResponse> likedFilmsByUser(final Long userId);
    Set<FilmResponse> getFriendFilms(final Long userId);
    Set<FilmResponse> getFilmsReviewedByFriend(final Long userId);
    Set<FilmResponse> getFilmsByCastAndCrewOccupation(final Long occupationId, final Long castId);
    List<FilmResponse> filterFilms(final FilmFilterRequest request);
}
