package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.FilmRateHistoryRequest;
import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.entity.FilmRateHistory;
import com.company.LetterboxdProject.repository.FilmRateHistoryRepository;
import com.company.LetterboxdProject.repository.FilmRepository;
import com.company.LetterboxdProject.repository.RatePointRepository;
import com.company.LetterboxdProject.repository.UserRepository;
import com.company.LetterboxdProject.service.inter.FilmRateHistoryServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmRateHistoryServiceImpl implements FilmRateHistoryServiceInter {

    private final FilmRateHistoryRepository filmRateHistoryRepository;
    private final RatePointRepository ratePointRepository;
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;

    @Override
    public Integer filmRating(Long FilmId) {
        Integer rating = 0;
        Set<Integer> ratePoints = filmRateHistoryRepository.findFilmRatePoint(FilmId);

        if(ratePoints.size() != 0) {
            for(Integer i : ratePoints) {
                rating +=i;
            }

            rating = rating / ratePoints.size();
            return rating;
        } else {
            return rating;
        }

    }

    @Override
    public void addLikedFilm(Long userId, Long filmId) {
        try {
            final FilmRateHistory filmRateHistory = this.findFilmRateHistory(userId, filmId);

            filmRateHistory.setLikes(true);
            filmRateHistory.setLikeDate(LocalDate.now());
            filmRateHistoryRepository.save(filmRateHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteLikedFilm(Long userId, Long filmId) {
        try{
            final FilmRateHistory filmRateHistory = this.findFilmRateHistory(userId, filmId);

            filmRateHistory.setLikes(false);
            filmRateHistory.setLikeDate(LocalDate.now());
            filmRateHistoryRepository.save(filmRateHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addWatchedFilm(Long userId, Long filmId) {
        try {
            final FilmRateHistory filmRateHistory = this.findFilmRateHistory(userId, filmId);

            filmRateHistory.setWatched(true);
            filmRateHistory.setWatchedDate(LocalDate.now());
            filmRateHistoryRepository.save(filmRateHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteWatchedFilm(Long userId, Long filmId) {
        try{
            final FilmRateHistory filmRateHistory = this.findFilmRateHistory(userId, filmId);

            filmRateHistory.setWatched(false);
            filmRateHistory.setWatchedDate(LocalDate.now());
            filmRateHistoryRepository.save(filmRateHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void giveRate(Long userId, Long filmId, FilmRateHistoryRequest rate) {
        try{
            final FilmRateHistory filmRateHistory = this.findFilmRateHistory(userId, filmId);
            filmRateHistory.setRatePoint(ratePointRepository.findByRate(rate.getRate()));
            filmRateHistory.setRateDate(LocalDate.now());
            if(filmRateHistory.getWatched() == null) {
                filmRateHistory.setWatched(true);
            }

            filmRateHistoryRepository.save(filmRateHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private FilmRateHistory findFilmRateHistory(final Long userId, final Long filmId) {
        FilmRateHistory filmRateHistory = filmRateHistoryRepository.findByUserAndFilm(userId, filmId);
        if(filmRateHistory == null) {
            filmRateHistory = new FilmRateHistory();
            filmRateHistory.setUser(userRepository.findById(userId).get());
            filmRateHistory.setFilm(filmRepository.findById(filmId).get());
            filmRateHistoryRepository.save(filmRateHistory);
        }

        return filmRateHistory;
    }
}
