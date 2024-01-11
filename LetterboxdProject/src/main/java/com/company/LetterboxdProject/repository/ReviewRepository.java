package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.film.id = ?1")
    Set<Review> findByFilmId(final Long filmId);

    @Query("select r from Review r where r.user.id in (select f.to.id from Follower f where f.from.id = ?1) and r.film.id = ?2")
    Set<Review> findByFriends(final Long friendId, final Long filmId);

//    @Query("select rlh.reviews from ReviewLikeHistory rlh where ")
//    Set<Review> findByReviewLikeHistories();

}
