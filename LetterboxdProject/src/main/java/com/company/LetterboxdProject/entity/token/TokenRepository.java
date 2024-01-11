package com.company.LetterboxdProject.entity.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join UserCredential u\s
      on t.user.id = u.user.id\s
      where u.user.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUserCredential(Integer id);


  Optional<Token> findByToken(String token);
}
