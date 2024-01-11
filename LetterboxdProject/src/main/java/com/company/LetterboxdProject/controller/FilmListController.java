package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.FilmListRequest;
import com.company.LetterboxdProject.dto.FilmListResponse;
import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.service.inter.FilmListService;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class FilmListController {

    private final FilmListService filmListService;
    private final FilmServiceInter filmService;

    @PostMapping("/new/{user}")
    public ResponseEntity<?> createFilmList(@PathVariable("user") Long userId,
            @RequestBody FilmListRequest filmListRequest) {
        final FilmListResponse filmListResponse = filmListService.createFilmList(userId, filmListRequest);

        return new ResponseEntity<>(filmListResponse, HttpStatus.OK);
    }

    @PutMapping("/edit/{user}/{id}")
    public ResponseEntity<?> editFilmList(@PathVariable("id") Long id,
                                           @PathVariable("user") Long userId,
                                           @RequestBody FilmListRequest filmListRequest) {
        final FilmListResponse response = filmListService.editFilmList(userId, id, filmListRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/add/user/{user}/list-name/{list}/film/{film}")
    public ResponseEntity<?> addFilmToList(@PathVariable("film") Long filmId,
                                           @PathVariable("user") Long userId,
                                            @PathVariable("list") Long listId) {
        final String response = filmListService.addFilm(userId, filmId, listId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/delete/user/{user}/list-name/{list}/film/{film}")
    public ResponseEntity<?> deleteFilmFromList(@PathVariable("film") Long filmId,
                                            @PathVariable("user") Long userId,
                                            @PathVariable("list") Long listId) {
        final String response = filmListService.deleteFilm(userId, filmId, listId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{user}/{list}")
    public ResponseEntity<?> deleteFilmList(@PathVariable("user") Long userId,
                                             @PathVariable("list") Long listId) {
        filmListService.deleteFilmList(userId, listId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/{list}")
    public ResponseEntity<?> getFilmsFromList(@PathVariable(value = "list") Long listId) {
        final List<FilmResponse> response = filmService.getFilmFromList(listId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/user/{user}")
    public ResponseEntity<?> getFilmListByUserId(@PathVariable("user") Long userId) {
        List<FilmListResponse> response = filmListService.getFilmListByUser(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{user}/{list}")
    public ResponseEntity<?> getFilmList(@PathVariable("user") Long userId,
                                                  @PathVariable("list") Long listId) {
        FilmListResponse response = filmListService.getFilmList(userId, listId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}