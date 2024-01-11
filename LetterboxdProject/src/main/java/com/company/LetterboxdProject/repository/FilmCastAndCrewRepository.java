package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.dto.CastAndCrewFilmRequest;
import com.company.LetterboxdProject.entity.CastAndCrew;
import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmCastAndCrew;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmCastAndCrewRepository extends JpaRepository<FilmCastAndCrew, Long>, JpaSpecificationExecutor<FilmCastAndCrew> {
    void deleteAllByFilm(final Film film);

    List<FilmCastAndCrew> findAll(Specification<FilmCastAndCrew> spec);

    @Query("select cc.film from FilmCastAndCrew cc where cc.occupations.id = ?1 and cc.castAndCrew.id = ?2")
    List<Film> findByOccupations(Long occupationId, Long castId);

}
