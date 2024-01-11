package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Long> {

    @Query("select t from Tags  t where t.tags in ?1")
    Set<Tags> findByTags(final Set<String> tags);


    Boolean existsTagsByTags(final String tags);
}
