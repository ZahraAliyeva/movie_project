package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.CastAndCrewRequest;
import com.company.LetterboxdProject.dto.CastAndCrewResponse;
import com.company.LetterboxdProject.entity.*;
import com.company.LetterboxdProject.repository.CastAndCrewRepository;
import com.company.LetterboxdProject.repository.FilmCastAndCrewRepository;
import com.company.LetterboxdProject.service.inter.CastAndCrewServiceInter;
import com.company.LetterboxdProject.util.CastAndCrewSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.LetterboxdProject.util.Mapper.*;

@Service
@RequiredArgsConstructor
public class CastAndCrewServiceImpl implements CastAndCrewServiceInter {

    private final CastAndCrewRepository castAndCrewRepository;
    private final FilmCastAndCrewRepository filmCastAndCrewRepo;
    private final CastAndCrewSpecification specification;

    @Override
    public CastAndCrewResponse saveCastAndCrew(final CastAndCrewRequest castAndCrewRequest) {
        final CastAndCrew castAndCrew = toModel(castAndCrewRequest);

//        final UUID uuid = UUID.randomUUID();
//        final URL url = storageService.uploadImage(uuid, castAndCrewRequest.getCastAndCrewPhotoUrl());

        castAndCrew.setCastAndCrewPhoto(new CastAndCrewPhoto(castAndCrewRequest.getCastAndCrewPhotoUrl()));
        castAndCrew.setCastAndCrewTmdbLink(new CastAndCrewTmdbLink(castAndCrewRequest.getCastAndCrewTmdbLink()));

        CastAndCrewResponse castAndCrewResponse = toResponse(castAndCrewRepository.save(castAndCrew));
        castAndCrewResponse.setFullName(castAndCrewRepository.findFullName(castAndCrew.getId()));

        return castAndCrewResponse;
    }

    @Override
    public CastAndCrewResponse edit(final Long id, final CastAndCrewRequest castAndCrewRequest) {
        final CastAndCrew castAndCrew = this.validateIfPresent(id);

        map(castAndCrewRequest, castAndCrew);

//        final UUID uuid = UUID.randomUUID();
//        final URL url = storageService.uploadImage(uuid, castAndCrewRequest.getCastAndCrewPhotoUrl());

        castAndCrew.setCastAndCrewPhoto(new CastAndCrewPhoto(castAndCrewRequest.getCastAndCrewPhotoUrl()));
        castAndCrew.getCastAndCrewTmdbLink().setCastAndCrewTmdbLink(castAndCrewRequest.getCastAndCrewTmdbLink());

        CastAndCrewResponse castAndCrewResponse = toResponse(castAndCrewRepository.save(castAndCrew));
        castAndCrewResponse.setFullName(castAndCrewRepository.findFullName(id));

        return castAndCrewResponse;
    }

    @Override
    public void deleteCastAndCrew(final Long id) {
        final CastAndCrew castAndCrew = this.validateIfPresent(id);
        castAndCrewRepository.delete(castAndCrew);

    }

    @Override
    public List<CastAndCrewResponse> getCastAndCrews() {
        final List<CastAndCrew> castAndCrews = castAndCrewRepository.findAll();
        return castAndCrews.stream().filter(g -> g != null).map(g -> {

            CastAndCrewResponse castAndCrewResponse = toResponse(g);
            castAndCrewResponse.setFullName(castAndCrewRepository.findFullName(g.getId()));

            return  castAndCrewResponse;
        }).toList();
    }

    @Override
    public CastAndCrewResponse getCastAndCrewById(final Long id) {
        final CastAndCrew castAndCrew = this.validateIfPresent(id);

        CastAndCrewResponse castAndCrewResponse = toResponse(castAndCrew);
        castAndCrewResponse.setFullName(castAndCrewRepository.findFullName(id));

        return castAndCrewResponse;
    }

    @Override
    public List<CastAndCrewResponse> getCastAndCrewByFilter(CastAndCrewRequest request) {
        final List<CastAndCrew> castAndCrews = castAndCrewRepository.findAll(specification.filterCastAndCrew(request));

        return castAndCrews.stream().filter(g -> g != null).map(g -> {

            CastAndCrewResponse castAndCrewResponse = toResponse(g);
            castAndCrewResponse.setFullName(castAndCrewRepository.findFullName(g.getId()));

            return  castAndCrewResponse;
        }).toList();
    }
    private CastAndCrew validateIfPresent(final Long id) {
        final Optional<CastAndCrew> castAndCrew = castAndCrewRepository.findById(id);
        return castAndCrew.get();
    }
}
