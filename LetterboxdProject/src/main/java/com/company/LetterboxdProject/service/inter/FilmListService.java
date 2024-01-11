package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.FilmListRequest;
import com.company.LetterboxdProject.dto.FilmListResponse;

import java.util.List;

public interface FilmListService {

    FilmListResponse createFilmList(final Long usedId, final FilmListRequest filmListRequest);
    FilmListResponse editFilmList(final Long usedId, final Long listId, final FilmListRequest filmListRequest);
    String addFilm(final Long userId, final Long filmId, final Long listId);
    String deleteFilm(final Long userId, final Long filmId, final Long listId);
    void deleteFilmList(final Long userId, final Long listId);
    List<FilmListResponse> getFilmListByUser(final Long userId);
    FilmListResponse getFilmList(final Long userId, final Long listId);
}
