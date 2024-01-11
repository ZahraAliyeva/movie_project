package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.FilmFilterRequest;
import com.company.LetterboxdProject.dto.FilmRequest;
import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private  final FilmServiceInter filmServiceInter;

    @GetMapping
    public ResponseEntity<?> getFilmList() {
        final List<FilmResponse> filmResponse = filmServiceInter.getFilms();

        return new ResponseEntity<>(filmResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilm(@PathVariable("id") Long id) {
        final FilmResponse filmResponse = filmServiceInter.getFilmById(id);

        return new ResponseEntity<>(filmResponse, HttpStatus.OK);
    }

    @GetMapping("/watched-by-friends/{userId}")
    public ResponseEntity<?> getFriendFilm(@PathVariable(name = "userId") Long id) {
        final Set<FilmResponse> responses = filmServiceInter.getFriendFilms(id);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/popular/this/week/with/friends//{userId}")
    public ResponseEntity<?> getFilmReviewedByFriends(@PathVariable(name = "userId") Long id) {
        final Set<FilmResponse> responses = filmServiceInter.getFilmsReviewedByFriend(id);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(value = {"/genre/{genre}", "/theme/{theme}", "/language/{language}", "/release/{release}", "/running-time/{running-time}"})
    public ResponseEntity<?> filterFilm(@PathVariable(value = "genre", required = false) String genre,
                                        @PathVariable(value = "theme", required = false) String theme,
                                        @PathVariable(value = "language", required = false) String language,
                                        @PathVariable(value = "release", required = false) Integer release,
                                        @PathVariable(value = "running-time", required = false) Integer runningTime) {
        FilmFilterRequest request = new FilmFilterRequest();

        request.setGenre(genre);
        request.setTheme(theme);
        request.setLanguage(language);
        request.setReleaseYear(release);
        request.setRunningTime(runningTime);

        final List<FilmResponse> response = filmServiceInter.filterFilms(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savedFilm(@RequestBody FilmRequest filmRequest) {
        final FilmResponse response = filmServiceInter.saveFilm(filmRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editFilm(@PathVariable("id") Long id, @RequestBody FilmRequest filmRequest) {
        final FilmResponse response = filmServiceInter.edit(id, filmRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable("id") Long id) {
        filmServiceInter.deleteFilm(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
