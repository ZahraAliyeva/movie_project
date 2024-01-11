package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Occupations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OccupationRepository extends JpaRepository<Occupations, Long> {

    @Query("SELECT o FROM Occupations o WHERE o.id in ?1")
    Set<Occupations> findAllByIds(final Set<Long> ids);
}
