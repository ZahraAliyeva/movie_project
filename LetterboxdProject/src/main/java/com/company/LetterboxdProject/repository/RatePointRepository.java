package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.RatePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatePointRepository extends JpaRepository<RatePoint, Long> {

    @Query("select rp FROM RatePoint rp where rp.rate = ?1")
    RatePoint findByRate(final Double rate);
}
