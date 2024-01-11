package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select r.user from Review r where r.id = ?1")
    User findUserByReviewId(final Long reviewId);

    @Query("SELECT u FROM UserCredential uc JOIN uc.user u WHERE uc.email = ?1")
    User findByCredentialUsername(final String username);

//    @Query("select fr.user from FilmRateHistory fr where fr.watched = true and fr.user.following")
//    Set<User> findByWatchedList(final Long userId);
}
