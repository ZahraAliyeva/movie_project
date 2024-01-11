package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.UserPronoun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PronounRepository extends JpaRepository<UserPronoun, Long> {
}
