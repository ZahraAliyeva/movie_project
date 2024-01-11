package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {

    @Query("select up from UserPhoto up where up.user.id = ?1")
    UserPhoto findByUser(Long userId);
}
