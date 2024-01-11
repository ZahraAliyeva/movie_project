package com.company.LetterboxdProject.security;

import com.company.LetterboxdProject.entity.*;
import com.company.LetterboxdProject.entity.token.Token;
import com.company.LetterboxdProject.entity.token.TokenRepository;
import com.company.LetterboxdProject.entity.token.TokenType;
import com.company.LetterboxdProject.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final WatchListRepository watchListRepository;
    private final FavoriteFilmRepository favoriteFilmRepository;


    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .registerDate(LocalDate.now())
                .userName(request.getUserName())
                .build();
        userRepository.save(user);
        var userCredential = UserCredential
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .user(user)
                .build();
        repository.save(userCredential);

        watchListRepository.save(new Watchlist(user));
        favoriteFilmRepository.save(new FavoriteFilm(user));

        var jwtToken = jwtService.generateToken(userCredential);
        revokeAllUserTokens(userCredential);
        saveUserToken(userCredential, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
    @Transactional
    public AuthenticationResponse authenticate (AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var userCredential=repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken= jwtService.generateToken(userCredential);
        revokeAllUserTokens(userCredential);
        saveUserToken(userCredential, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(UserCredential user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUserCredential(user.getUser().getId().intValue());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(UserCredential user, String jwtToken) {
        var token = Token.builder()
                .user(user.getUser())
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
