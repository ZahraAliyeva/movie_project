package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmWatchlist;
import com.company.LetterboxdProject.entity.Watchlist;
import com.company.LetterboxdProject.repository.FilmNameRepository;
import com.company.LetterboxdProject.repository.FilmRepository;
import com.company.LetterboxdProject.repository.FilmWatchlistRepository;
import com.company.LetterboxdProject.repository.WatchListRepository;
import com.company.LetterboxdProject.service.inter.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WatchListServiceImpl implements WatchListService {

    private final WatchListRepository watchRepository;
    private final FilmRepository filmRepository;
    private final FilmNameRepository filmNameRepository;
    private final FilmWatchlistRepository repository;
    @Transactional
    @Override
    public String addFilmToWatchlist(Long userId, Long filmId) {
        try {
            final Watchlist watchlist = watchRepository.findByUserId(userId);
            final Film film = filmRepository.findById(filmId).get();

            final FilmWatchlist filmWatchlist = new FilmWatchlist();
            filmWatchlist.setFilm(film);
            filmWatchlist.setWatchlist(watchlist);
            filmWatchlist.setDate(LocalDate.now());

            repository.save(filmWatchlist);

            return filmNameRepository
                    .findFilmNameByLanguageIsAndFilmAndId("English", filmId) +
                    " add to your watchlist(" + watchlist.getId().toString() +")";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  "";
    }
    @Transactional
    @Override
    public String deleteFilmFromWatchlist(Long userId, Long filmId) {
        try {
            final Watchlist watchlist = watchRepository.findByUserId(userId);
            final Film film = filmRepository.findById(filmId).get();

            final FilmWatchlist filmWatchlist = repository.findByWatchlistAndFilm(watchlist, film);

            if(filmWatchlist != null) {
                repository.delete(filmWatchlist);
            }

            return filmNameRepository
                    .findFilmNameByLanguageIsAndFilmAndId("English", filmId) +
                    " delete from your watchlist(" + watchlist.getId().toString() +")";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
