package com.company.LetterboxdProject.repository;

import com.company.LetterboxdProject.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {

    Optional<UserCredential> findByEmail(String email);

    @Query("select t from UserCredential t where t.email=:username")
    Optional<UserCredential> findByUsername(String username);
}
