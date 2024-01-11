package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.Film;
import com.company.LetterboxdProject.entity.Review;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long>, JpaSpecificationExecutor<Film> {
    @Query("select fw.film from FilmWatchlist fw where fw.watchlist.id = ?1")
    Set<Film> findFilmByWatchId(final Long id);

    @Query("select f from Film f where f.id in ?1")
    Set<Film> findAllByIds(final Set<Long> ids);

    @Query("select fl.films from FilmList fl where fl.id = ?1")
    Set<Film> findFilmByList(final Long id);

    @Query("select fr.film from FilmRateHistory fr where fr.watched = true and fr.user.id in (select f.to.id from Follower f where f.from.id = ?1) order by fr.watchedDate desc ")
    Set<Film> findWatchByFriend(final Long userId);

    @Query("select r.film from Review r where r.user.id in (select f.to.id from Follower f where f.from.id = ?1)")
    Set<Film> findFilmReviewedByFriends(final Long friendId);

}
