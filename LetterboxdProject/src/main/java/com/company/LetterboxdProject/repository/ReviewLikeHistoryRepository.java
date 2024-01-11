package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.ReviewLikeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeHistoryRepository extends JpaRepository<ReviewLikeHistory, Long> {

    @Query("select rl from ReviewLikeHistory rl where rl.user.id = ?1 and rl.reviews.id = ?2")
    ReviewLikeHistory findByUserAndReview(final Long userId, final Long reviewId);

    @Query("select count(rl) from ReviewLikeHistory rl where rl.reviews.id = ?1 and rl.likes = true")
    Long findByLikes(final Long reviewId);
}
