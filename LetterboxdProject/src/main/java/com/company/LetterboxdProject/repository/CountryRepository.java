package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.id in ?1")
    Set<Country> findAllByIds(final Set<Long> ids);
}
