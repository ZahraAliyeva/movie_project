package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.FilmCastAndCrew;
import com.company.LetterboxdProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.id in ?1")
    Set<Genre> findAllByIds(final Set<Long> ids);
}
