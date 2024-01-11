package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    @Query("select c from ReviewComment c where c.reviews.id = ?1")
    Set<ReviewComment> findByReviews(final Long reviewId);
}
