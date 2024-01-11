package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.FavoriteFilm;
import com.company.LetterboxdProject.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FavoriteFilmRepository extends JpaRepository<FavoriteFilm, Long> {

    @Query("select ff.films from FavoriteFilm ff where ff.user.id = ?1")
    Set<Film> findByUserId(final Long userId);
    @Query("select ff from FavoriteFilm ff where ff.user.id = ?1")
    FavoriteFilm findByUser(final Long userId);
}
