package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("SELECT t FROM Theme t WHERE t.id in ?1")
    Set<Theme> findAllByIds(final Set<Long> ids);
}
