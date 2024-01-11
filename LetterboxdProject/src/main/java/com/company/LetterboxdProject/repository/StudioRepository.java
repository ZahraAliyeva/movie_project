package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    @Query("SELECT s FROM Studio s WHERE s.id in ?1")
    Set<Studio> findAllByIds(final Set<Long> ids);
}
