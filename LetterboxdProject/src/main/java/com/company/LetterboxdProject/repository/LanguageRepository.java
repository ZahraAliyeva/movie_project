package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("SELECT l FROM Language l WHERE l.id in ?1")
    Set<Language> findAllByIds(final Set<Long> ids);
}
