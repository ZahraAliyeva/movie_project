package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Follower;
import com.company.LetterboxdProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    @Query("SELECT f.from FROM Follower f where f.to = ?1 ")
    Set<User> getFollower(final User user);
    @Query("SELECT f.to FROM Follower f where f.from = ?1 ")
    Set<User> getFollowing(final User user);
    @Query("select f from Follower f where f.from = ?1 and f.to = ?2")
    Follower findByFromAndTo(final User from, final User to);
}
