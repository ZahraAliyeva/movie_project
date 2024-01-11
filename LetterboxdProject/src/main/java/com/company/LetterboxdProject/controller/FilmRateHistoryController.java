package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.FilmRateHistoryRequest;
import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.service.inter.FilmRateHistoryServiceInter;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import com.company.LetterboxdProject.service.inter.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/film-rate-history")
@RequiredArgsConstructor
public class FilmRateHistoryController {

    private final FilmRateHistoryServiceInter serviceInter;
    private final WatchListService watchListService;
    private final FilmServiceInter filmServiceInter;


    @GetMapping("/watched/by/{user}")
    public ResponseEntity<?> getWatchedFilmsByUser(@PathVariable("user") Long userId) {
        Set<FilmResponse> responses = filmServiceInter.watchedFilmsByUser(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/liked/by/{user}")
    public ResponseEntity<?> getLikedFilmsByUser(@PathVariable("user") Long userId) {
        Set<FilmResponse> responses = filmServiceInter.likedFilmsByUser(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/like/user/{user}/film/{filmId}")
    private ResponseEntity<?> addFilmToLikedList(@PathVariable("user") Long userId,
                                                @PathVariable("filmId") Long filmId) {
        serviceInter.addLikedFilm(userId, filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/dislike/user/{user}/film/{filmId}")
    private ResponseEntity<?> deleteFilmFromLikedList(@PathVariable("user") Long userId,
                                                @PathVariable("filmId") Long filmId) {
        serviceInter.deleteLikedFilm(userId, filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/watched/user/{user}/film/{filmId}")
    private ResponseEntity<?> addFilmToWatchedList(@PathVariable("user") Long userId,
                                                @PathVariable("filmId") Long filmId) {
        serviceInter.addWatchedFilm(userId, filmId);
        watchListService.deleteFilmFromWatchlist(userId, filmId);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/unwatched/user/{user}/film/{filmId}")
    private ResponseEntity<?> deleteFilmFromWatchedList(@PathVariable("user") Long userId,
                                                     @PathVariable("filmId") Long filmId) {
        serviceInter.deleteWatchedFilm(userId, filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/rating/user/{user}/film/{filmId}")
    private ResponseEntity<?> givenRate (@PathVariable("user") Long userId,
                                         @PathVariable("filmId") Long filmId,
                                         @RequestBody FilmRateHistoryRequest rate) {
        serviceInter.giveRate(userId, filmId, rate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}