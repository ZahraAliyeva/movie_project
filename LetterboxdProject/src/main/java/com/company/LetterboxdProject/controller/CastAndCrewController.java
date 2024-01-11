package com.company.LetterboxdProject.controller;

import com.company.LetterboxdProject.dto.CastAndCrewRequest;
import com.company.LetterboxdProject.dto.CastAndCrewResponse;
import com.company.LetterboxdProject.dto.FilmResponse;
import com.company.LetterboxdProject.service.inter.CastAndCrewServiceInter;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/cast_and_crew")
@RequiredArgsConstructor
public class CastAndCrewController {

    private final CastAndCrewServiceInter castAndCrewService;
    private final FilmServiceInter filmServiceInter;

    @GetMapping
    public ResponseEntity<?> getCastAndCrewList() {
        final List<CastAndCrewResponse> responses = castAndCrewService.getCastAndCrews();

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCastAndCrew(@PathVariable("id") Long id) {
        final CastAndCrewResponse response = castAndCrewService.getCastAndCrewById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = {"/name/{name}", "/surname/{surname}"})
    public ResponseEntity<?> getFilterCastAndCrew(@PathVariable(value = "name", required = false) String name,
                                                  @PathVariable(value = "surname", required = false) String surname) {
        CastAndCrewRequest request = new CastAndCrewRequest();
        request.setName(name);
        request.setSurname(surname);
        final List<CastAndCrewResponse> response = castAndCrewService.getCastAndCrewByFilter(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{occupation}/{id}")
    public ResponseEntity<?> getCastAndCrewFilmByOccupation(@PathVariable(value = "occupation") Long occupation,
                                                        @PathVariable(value = "id") Long id) {
        final Set<FilmResponse> response = filmServiceInter.getFilmsByCastAndCrewOccupation(occupation, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> savedCastAndCrew(@RequestBody CastAndCrewRequest castAndCrewRequest) {

        final CastAndCrewResponse response = castAndCrewService.saveCastAndCrew(castAndCrewRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCastAndCrew(@PathVariable("id") Long id, @RequestBody CastAndCrewRequest castAndCrewRequest) {
        final CastAndCrewResponse response = castAndCrewService.edit(id, castAndCrewRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCastAndCrew(@PathVariable("id") Long id) {
        castAndCrewService.deleteCastAndCrew(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}