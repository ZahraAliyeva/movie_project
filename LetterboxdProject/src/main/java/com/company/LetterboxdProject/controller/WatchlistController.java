package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import com.company.LetterboxdProject.service.inter.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchListService service;
    private final FilmServiceInter filmService;

    @PutMapping("/add/user/{user}/film/{film}")
    public ResponseEntity<?> addFilmToWatchlist(@PathVariable(value = "user", required = false) Long userId,
                                     @PathVariable(value = "film", required = false) Long filmId) {
        final String response = service.addFilmToWatchlist(userId,filmId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<?> getWatchlist(@PathVariable(value = "user", required = false) Long userId) {
        final List<FilmResponse> response = filmService.getWatchList(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{user}/film/{film}")
    public ResponseEntity<?> deleteFilmFromWatchlist(@PathVariable(value = "user", required = false) Long userId,
                                                     @PathVariable(value = "film", required = false) Long filmId) {
        final String response = service.deleteFilmFromWatchlist(userId, filmId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
