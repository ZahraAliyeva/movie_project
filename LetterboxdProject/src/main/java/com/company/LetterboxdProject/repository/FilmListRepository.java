package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.FilmList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FilmListRepository extends JpaRepository<FilmList, Long> {

    @Query("select fl from FilmList fl where fl.user.id = ?1")
    Set<FilmList> findByUser(final Long userId);
}
