package com.company.LetterboxdProject.util;

import com.company.LetterboxdProject.dto.*;
import com.company.LetterboxdProject.entity.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Set;
import java.util.stream.Collectors;

public class Mapper {

    public static CastAndCrew toModel(final CastAndCrewRequest castAndCrewRequest) {
        return convertTo(castAndCrewRequest, CastAndCrew.class);
    }
    public static Film toModel(final FilmRequest filmRequest) {
        return convertTo(filmRequest, Film.class);
    }
    public static FilmList toModel(final FilmListRequest filmListRequest) {
        return convertTo(filmListRequest, FilmList.class);
    }
    public static Review toModel(final ReviewRequest reviewRequest) {
        return convertTo(reviewRequest, Review.class);
    }
    public static ReviewComment toModel(final CommentRequest request) {
        return convertTo(request, ReviewComment.class);
    }
    public static FilmName toModel(final FilmNameRequest filmNameRequest, final Film film) {
        final FilmName filmName = convertTo(filmNameRequest, FilmName.class);
        filmName.setFilm(film);
        return filmName;
    }
    public static Poster toModel(final PosterRequest posterRequest, final Film film) {
        final Poster poster = convertTo(posterRequest, Poster.class);
        poster.setFilm(film);
        return poster;
    }
    public static Release toModel(final ReleaseRequest releaseRequest, final Film film) {
        final Release release = convertTo(releaseRequest, Release.class);
        release.setFilm(film);
        return release;
    }
    public static FilmLanguage toModel(final FilmLanguageRequest filmLanguageRequest, final Film film) {
        final FilmLanguage language = convertTo(filmLanguageRequest, FilmLanguage.class);
        language.setFilm(film);

        return language;
    }
    public static FilmCastAndCrew toModel(final FilmCastAndCrewRequest filmCastAndCrew, final Film film) {
        final FilmCastAndCrew castAndCrew = convertTo(filmCastAndCrew, FilmCastAndCrew.class);
        castAndCrew.setFilm(film);

        return castAndCrew;
    }
    public static CastAndCrewResponse toResponse(final CastAndCrew castAndCrew) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(castAndCrewResponseMapping);

        return convertTo(mapper, castAndCrew, CastAndCrewResponse.class);
    }
    public static FilmResponse toResponse(final Film film) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(filmResponsePropertyMap);

        return convertTo(mapper, film, FilmResponse.class);
    }
    public static FilmListResponse  toResponse(final FilmList filmList) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(filmListResponseMapping);

        return convertTo(mapper, filmList, FilmListResponse.class);
    }
    public static UserResponse toResponse(final User user) {
        final ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponse.class);
    }
    public static ReviewResponse toResponse(final Review review) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(reviewResponsePropertyMap);

        return convertTo(mapper, review, ReviewResponse.class);
    }
    public static CommentResponse toResponse(final ReviewComment comment) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(commentResponsePropertyMap);

        return convertTo(mapper, comment, CommentResponse.class);
    }
    public static void map(final CastAndCrewRequest castAndCrewRequest, final CastAndCrew castAndCrew) {
        final ModelMapper mapper = new ModelMapper();
        mapper.map(castAndCrewRequest, castAndCrew);
    }
    public static void map(final FilmRequest filmRequest, final Film film) {
        final ModelMapper mapper = new ModelMapper();
        mapper.addMappings(filmChildrenSkip);

        mapper.map(filmRequest, film);
    }
    public static void map(final FilmListRequest request, final FilmList filmList) {
        final ModelMapper mapper = new ModelMapper();

        mapper.map(request, filmList);
    }
    public static void map(final UserRequest request, final User user) {
        final ModelMapper mapper = new ModelMapper();

        mapper.map(request, user);
    }
    public static void map(final ReviewRequest request, final Review review) {
        final ModelMapper mapper = new ModelMapper();

        mapper.map(request, review);
    }
    public static void map(final CommentRequest request, final ReviewComment comment) {
        final ModelMapper mapper = new ModelMapper();

        mapper.map(request, comment);
    }
    private static <S, T> T convertTo(final S source, Class<T> targetClass) {
        return convertTo(new ModelMapper(), source, targetClass);
    }
    public  static <S, T> T convertTo(final ModelMapper mapper, final S source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }

    private static PropertyMap<CastAndCrew, CastAndCrewResponse> castAndCrewResponseMapping = new PropertyMap<CastAndCrew, CastAndCrewResponse>() {
        @Override
        protected void configure() {
            map(source.getCastAndCrewTmdbLink().getCastAndCrewTmdbLink()).setCastAndCrewTmdbLink(null);
            map(source.getCastAndCrewPhoto().getCastAndCrewPhotoUrl()).setCastAndCrewPhotoUrl(null);
        }
    };
    private static PropertyMap<FilmList, FilmListResponse> filmListResponseMapping = new PropertyMap<FilmList, FilmListResponse>() {
        @Override
        protected void configure() {
            final Converter<Set<Film>, Set<Long>> convertFilm = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Film::getId)
                    .collect(Collectors.toSet());

            final Converter<FilmListVisibleTo, Long> convertFLVT = ctx -> ctx.getSource() == null? null : ctx.getSource().getId();

            final Converter<Set<Tags>, Set<String>> convertTags = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Tags::getTags)
                    .collect(Collectors.toSet());

            using(convertFilm).map(source.getFilms()).setFilms(null);
            using(convertFLVT).map(source.getFilmListVisibleTo()).setFilmListVisibleTo(null);
            using(convertTags).map(source.getTags()).setTags(null);
        }
    };
    private static PropertyMap<Review, ReviewResponse> reviewResponsePropertyMap = new PropertyMap<Review, ReviewResponse>() {
        @Override
        protected void configure() {

            final Converter<User, Long> convertUser = context -> context.getSource() == null? null : context.getSource().getId();

            final Converter<Set<Tags>, Set<String>> convertTags = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Tags::getTags)
                    .collect(Collectors.toSet());

            using(convertUser).map(source.getUser()).setUser(null);
            using(convertTags).map(source.getTags()).setTags(null);
        }
    };
    private static PropertyMap<ReviewComment, CommentResponse> commentResponsePropertyMap = new PropertyMap<ReviewComment, CommentResponse>() {
        @Override
        protected void configure() {
            final Converter<User, Long> convertUser = context -> context.getSource() == null? null : context.getSource().getId();

            final Converter<Set<Tags>, Set<String>> convertTags = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Tags::getTags)
                    .collect(Collectors.toSet());

            using(convertUser).map(source.getUser()).setUser(null);
            using(convertTags).map(source.getTags()).setTags(null);
        }
    };
    private static PropertyMap<Film, FilmResponse> filmResponsePropertyMap = new PropertyMap<Film, FilmResponse>() {
        @Override
        protected void configure() {
            final Converter<Set<Studio>, Set<Long>> converterStudio = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Studio::getId)
                    .collect(Collectors.toSet());

            final Converter<Set<Country>, Set<Long>> converterCountry = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Country::getId)
                    .collect(Collectors.toSet());

            final Converter<Set<Genre>, Set<Long>> converterGenre = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Genre::getId)
                    .collect(Collectors.toSet());

            final Converter<Set<Theme>, Set<Long>> converterTheme = ctx -> ctx.getSource() == null? null : ctx.getSource()
                    .stream()
                    .map(Theme::getId)
                    .collect(Collectors.toSet());

            using(converterStudio).map(source.getStudios()).setStudioIds(null);
            using(converterCountry).map(source.getCountries()).setCountryIds(null);
            using(converterGenre).map(source.getGenres()).setGenresIds(null);
            using(converterTheme).map(source.getThemes()).setThemeIds(null);

            map(source.getFilmLinks().getImdbLink()).setImdbLink(null);
            map(source.getFilmLinks().getTmdbLink()).setTmdbLink(null);
            map(source.getFilmLinks().getTrailer()).setTrailer(null);
            map(source.getPosters()).setPosterResponse(null);
            map(source.getReleases()).setReleaseResponse(null);
        }
    };

    private static PropertyMap<FilmRequest, Film> filmChildrenSkip = new PropertyMap<FilmRequest, Film>() {
        @Override
        protected void configure() {
            skip(destination.getFilmName());
            skip(destination.getPosters());
            skip(destination.getReleases());
            skip(destination.getLanguages());
            skip(destination.getFilmCastAndCrews());
        }
    };
}