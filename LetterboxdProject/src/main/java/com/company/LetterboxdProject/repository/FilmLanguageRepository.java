package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmLanguageRepository extends JpaRepository<FilmLanguage, Long> {

    @Query("SELECT fl.language.language FROM FilmLanguage fl WHERE fl.originalLanguage = true and fl.film.id = ?1")
    String findFilmLanguageByOriginalLanguageIsTrue(final Long id);

    @Query("SELECT fl.language.language FROM FilmLanguage fl WHERE fl.originalLanguage = false and fl.film.id = ?1")
    List<String> findFilmLanguageByOriginalLanguageIsFalse(final Long id);

    void deleteAllByFilm(final Film film);
}
