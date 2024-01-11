package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Long> {

    void deleteAllByFilm(final Film film);
}
