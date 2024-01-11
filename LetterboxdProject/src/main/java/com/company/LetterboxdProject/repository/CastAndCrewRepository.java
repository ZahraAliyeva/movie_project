package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.dto.CastAndCrewResponse;
import com.company.LetterboxdProject.entity.CastAndCrew;
import com.company.LetterboxdProject.entity.Film;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastAndCrewRepository extends JpaRepository<CastAndCrew, Long>, JpaSpecificationExecutor<CastAndCrew> {

    @Query("SELECT CONCAT(c.name,' ', c.surname) FROM CastAndCrew c WHERE c.id = ?1")
    String findFullName(final Long id);

    List<CastAndCrew> findAll(Specification<CastAndCrew> spec);
}
