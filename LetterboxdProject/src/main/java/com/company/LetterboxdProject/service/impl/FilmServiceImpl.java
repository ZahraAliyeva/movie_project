package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.dto.*;
import com.company.LetterboxdProject.entity.*;
import com.company.LetterboxdProject.repository.*;
import com.company.LetterboxdProject.service.inter.FilmRateHistoryServiceInter;
import com.company.LetterboxdProject.service.inter.FilmServiceInter;
import com.company.LetterboxdProject.util.FilmSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.LetterboxdProject.util.Mapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmServiceInter {

    private final FilmRepository filmRepository;
    private final StudioRepository studioRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;
    private final FilmNameRepository filmNameRepository;
    private final ReleaseRepository releaseRepository;
    private final FilmRateHistoryServiceInter filmRateHistoryService;
    private final FilmLanguageRepository filmLanguageRepository;
    private final OccupationRepository occupationRepository;
    private final CastAndCrewRepository castAndCrewRepository;
    private final PosterRepository posterRepository;
    private final FilmCastAndCrewRepository filmCastAndCrewRepository;
    private final WatchListRepository watchListRepository;
    private final FilmRateHistoryRepository filmRateHistoryRepository;
    private final FilmListRepository filmListRepository;
    private final FilmSpecification specification;

    @Override
    public FilmResponse saveFilm(FilmRequest filmRequest) {
        final Film film = toModel(filmRequest);

        final Set<Studio> studios = studioRepository.findAllByIds(filmRequest.getStudioIds());
        final Set<Country> countries = countryRepository.findAllByIds(filmRequest.getCountryIds());
        final Set<Genre> genres = genreRepository.findAllByIds(filmRequest.getGenresIds());
        final Set<Theme> themes = themeRepository.findAllByIds(filmRequest.getThemeIds());

        film.setStudios(studios);
        film.setCountries(countries);
        film.setGenres(genres);
        film.setThemes(themes);

        film.setFilmLinks(new FilmLinks(filmRequest.getTmdbLink(), filmRequest.getImdbLink(), filmRequest.getTrailer()));

        film.setReleases(filmRequest.getReleaseRequests().stream().map(r -> this.savedRelease(r, film)).collect(Collectors.toSet()));
        film.setFilmName(filmRequest.getFilmNameRequest().stream().map(f -> this.savedFilmName(f, film)).collect(Collectors.toSet()));
        film.setPosters(filmRequest.getPosterRequests().stream().map(p -> toModel(p, film)).collect(Collectors.toSet()));
        film.setLanguages(filmRequest.getLanguageRequests().stream().map(l -> this.savedFilmLanguage(l, film)).collect(Collectors.toSet()));
        film.setFilmCastAndCrews(filmRequest.getFilmCastAndCrewRequests().stream().map(c -> savedCastAndCrew(c, film)).collect(Collectors.toSet()));

        FilmResponse filmResponse = filmToResponse(filmRepository.save(film));

        return filmResponse;
    }
    private FilmName savedFilmName(FilmNameRequest filmNameRequest, Film film) {
        final FilmName filmName = toModel(filmNameRequest, film);
        final Language language = languageRepository.getById(filmNameRequest.getLanguage());
        filmName.setLanguage(language);

        return filmName;
    }
    private FilmLanguage savedFilmLanguage(FilmLanguageRequest filmLanguageRequest, Film film) {
        final FilmLanguage filmLanguage  = toModel(filmLanguageRequest, film);
        final Language language = languageRepository.getById(filmLanguageRequest.getLanguage());
        filmLanguage.setLanguage(language);

        return filmLanguage;
    }
    private Release savedRelease(ReleaseRequest releaseRequest, Film film) {
        final Release release = toModel(releaseRequest, film);
        final Country country = countryRepository.getById(releaseRequest.getCountry());
        release.setCountry(country);

        return release;
    }
    private FilmCastAndCrew savedCastAndCrew (FilmCastAndCrewRequest request, Film film) {
        final FilmCastAndCrew filmCastAndCrew = toModel(request, film);
        final Occupations occupations = occupationRepository.getById(request.getOccupations());
        final CastAndCrew castAndCrew = castAndCrewRepository.getById(request.getCastAndCrew());

        filmCastAndCrew.setOccupations(occupations);
        filmCastAndCrew.setCastAndCrew(castAndCrew);

        return filmCastAndCrew;
    }
    @Override
    public FilmResponse edit(final Long id, final FilmRequest filmRequest) {
        final Film film = this.validateIfPresent(id);

        map(filmRequest, film);

        final Set<Studio> studios = studioRepository.findAllByIds(filmRequest.getStudioIds());
        final Set<Country> countries = countryRepository.findAllByIds(filmRequest.getCountryIds());
        final Set<Genre> genres = genreRepository.findAllByIds(filmRequest.getGenresIds());
        final Set<Theme> themes = themeRepository.findAllByIds(filmRequest.getThemeIds());

        film.setStudios(studios);
        film.setCountries(countries);
        film.setGenres(genres);
        film.setThemes(themes);

        film.getFilmLinks().setImdbLink(filmRequest.getImdbLink());
        film.getFilmLinks().setTmdbLink(filmRequest.getTmdbLink());
        film.getFilmLinks().setTrailer(filmRequest.getTrailer());

        this.updateReleases(film, filmRequest.getReleaseRequests());
        this.updateFilmName(film, filmRequest.getFilmNameRequest());
        this.updatePoster(film, filmRequest.getPosterRequests());
        this.updateFilmLanguage(film, filmRequest.getLanguageRequests());
        this.updateCastAdCrew(film, filmRequest.getFilmCastAndCrewRequests());

        FilmResponse filmResponse = filmToResponse(filmRepository.save(film));
//        FilmResponse response = toResponse(filmRepository.save(film));
        return filmResponse;
    }
    private void updateReleases(final Film film, final Set<ReleaseRequest> releaseRequests) {
        releaseRepository.deleteAllByFilm(film);

        film.setReleases(releaseRequests.stream().map(r -> this.savedRelease(r, film)).collect(Collectors.toSet()));
    }
    private void updateFilmName(final Film film, final Set<FilmNameRequest> filmNameRequests) {
        filmNameRepository.deleteAllByFilm(film);

        film.setFilmName(filmNameRequests.stream().map(r -> this.savedFilmName(r, film)).collect(Collectors.toSet()));
    }
    private void updatePoster(final Film film, final Set<PosterRequest> posterRequests) {
        posterRepository.deleteAllByFilm(film);

        film.setPosters(posterRequests.stream().map(p -> toModel(p, film)).collect(Collectors.toSet()));
    }
    private void updateFilmLanguage(final Film film, final Set<FilmLanguageRequest> filmLanguageRequests) {
        filmLanguageRepository.deleteAllByFilm(film);

        film.setLanguages(filmLanguageRequests.stream().map(p -> this.savedFilmLanguage(p, film)).collect(Collectors.toSet()));
    }
    private void updateCastAdCrew(final Film film, final Set<FilmCastAndCrewRequest> filmCastAndCrewRequests) {
        filmCastAndCrewRepository.deleteAllByFilm(film);

        film.setFilmCastAndCrews(filmCastAndCrewRequests.stream().map(p -> this.savedCastAndCrew(p, film)).collect(Collectors.toSet()));
    }
    @Override
    public void deleteFilm(Long id) {
        final Film film = this.validateIfPresent(id);
        filmRepository.delete(film);

    }
    @Override
    public List<FilmResponse> getFilms() {
        final  List<Film> films = filmRepository.findAll();

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).toList();
    }
    @Override
    public FilmResponse getFilmById(Long id) {
        final Film film = this.validateIfPresent(id);

        FilmResponse filmResponse = filmToResponse(film);

        return filmResponse;
    }

    @Override
    public List<FilmResponse> getWatchList(Long userId) {
        final Watchlist watchlist = watchListRepository.findByUserId(userId);
        Set<Film> films = filmRepository.findFilmByWatchId(watchlist.getId());

        return films.stream().filter(f -> f != null).map(f -> {

            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).toList();
    }

    @Override
    public List<FilmResponse> getFilmFromList(Long listId) {
        final FilmList filmList = filmListRepository.findById(listId).get();
        Set<Film> films = filmList.getFilms();

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).toList();
    }

    @Override
    public Set<FilmResponse> watchedFilmsByUser(Long userId) {
        final Set<Film> films = filmRateHistoryRepository.findFilmsByWatched(userId);

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<FilmResponse> likedFilmsByUser(Long userId) {
        final Set<Film> films = filmRateHistoryRepository.findFilmsByLiked(userId);

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<FilmResponse> getFriendFilms(Long userId) {
        Set<Film> films = filmRepository.findWatchByFriend(userId);

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<FilmResponse> getFilmsReviewedByFriend(Long userId) {
        Set<Film> films = filmRepository.findFilmReviewedByFriends(userId);

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public Set<FilmResponse> getFilmsByCastAndCrewOccupation(Long occupationId, Long castId) {
        List<Film> films = filmCastAndCrewRepository.findByOccupations(occupationId, castId);

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).collect(Collectors.toSet());
    }

    @Override
    public List<FilmResponse> filterFilms(FilmFilterRequest request) {
        final List<Film> films = filmRepository.findAll(specification.filterFilmByCastAndCrew(request));

        return films.stream().filter(f -> f != null).map(f -> {
            FilmResponse filmResponse = filmToResponse(f);
            return filmResponse;
        }).toList();
    }

    private FilmResponse filmToResponse(Film film) {
        FilmResponse filmResponse = toResponse(film);

        filmResponse.setOriginalName(filmNameRepository.findFilmNameByOriginalNameIsTrueAndFilmId(film.getId()));
        filmResponse.setEnglishName(filmNameRepository.findFilmNameByLanguageIsAndFilmAndId("English", film.getId()));
        filmResponse.setAlternativeTitles(filmNameRepository.findFilmNameByOriginalNameIsFalse(film.getId()));
        filmResponse.setReleaseYear(releaseRepository.findFirstByFilmId(film.getId()).getDate().getYear());
        filmResponse.setOriginalLanguages(filmLanguageRepository.findFilmLanguageByOriginalLanguageIsTrue(film.getId()));
        filmResponse.setSpokenLanguages(filmLanguageRepository.findFilmLanguageByOriginalLanguageIsFalse(film.getId()));
        filmResponse.setRating(filmRateHistoryService.filmRating(film.getId()));
        filmResponse.setNumOfWatched(filmRateHistoryRepository.findByWatched(film.getId()));
        filmResponse.setNumOfLike(filmRateHistoryRepository.findByLikes(film.getId()));

        return filmResponse;
    }
    private Film validateIfPresent(final Long id) {
        final Optional<Film> film = filmRepository.findById(id);
        return film.get();
    }
}