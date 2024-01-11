package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    @Query("SELECT r FROM Release r WHERE r.id in ?1")
    Set<Release> findAllByIds(final Set<Long> ids);

    Release findFirstByFilmId(final Long id);

    void deleteAllByFilm(final Film film);

    @Query("DELETE FROM Release r where r.film.id = ?1")
    void deleteAllByFilmId(final Long id);
}
