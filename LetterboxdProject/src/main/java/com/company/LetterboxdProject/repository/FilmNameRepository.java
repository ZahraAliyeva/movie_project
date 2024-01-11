package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.FilmName;
import com.company.LetterboxdProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmNameRepository extends JpaRepository<FilmName,Long> {

    @Query("SELECT fn.filmName FROM FilmName fn WHERE fn.originalName = true and fn.film.id = ?1")
    String findFilmNameByOriginalNameIsTrueAndFilmId(final Long id);

    @Query("SELECT fn.filmName FROM FilmName fn WHERE fn.language.language = ?1 and fn.film.id = ?2")
    String findFilmNameByLanguageIsAndFilmAndId(final String language, final Long id);

    @Query("SELECT fn.filmName from FilmName fn where fn.language.language <> 'English' and  fn.originalName = false and fn.film.id = ?1")
    List<String> findFilmNameByOriginalNameIsFalse(final Long id);

    void deleteAllByFilm(final Film film);
}
