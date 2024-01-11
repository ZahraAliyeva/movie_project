package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.FilmListVisibleTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmListVisibleToRepository extends JpaRepository<FilmListVisibleTo, Long> {
}
