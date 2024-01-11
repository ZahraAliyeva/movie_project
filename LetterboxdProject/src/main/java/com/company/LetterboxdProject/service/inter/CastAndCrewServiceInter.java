package com.company.LetterboxdProject.service.inter;

import com.company.LetterboxdProject.dto.CastAndCrewRequest;
import com.company.LetterboxdProject.dto.CastAndCrewResponse;

import java.util.List;
import java.util.Optional;

public interface CastAndCrewServiceInter {

    CastAndCrewResponse saveCastAndCrew(final CastAndCrewRequest castAndCrewRequest);
    CastAndCrewResponse edit(final Long id, final CastAndCrewRequest castAndCrewRequest);
    void deleteCastAndCrew(final Long id);
    List<CastAndCrewResponse> getCastAndCrews();
    CastAndCrewResponse getCastAndCrewById(final Long id);
    List<CastAndCrewResponse> getCastAndCrewByFilter(CastAndCrewRequest request);
}
