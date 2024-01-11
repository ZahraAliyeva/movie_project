package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.entity.FilmLinks;
import com.company.LetterboxdProject.repository.FilmLinkRepository;
import com.company.LetterboxdProject.service.inter.FilmLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmLinkServiceImpl implements FilmLinkService {

    private final FilmLinkRepository repository;

    @Override
    public FilmLinks create(FilmLinks filmLinks) {
        return repository.save(filmLinks);
    }
}
