package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.FilmLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmLinkRepository extends JpaRepository<FilmLinks,Long> {
}
