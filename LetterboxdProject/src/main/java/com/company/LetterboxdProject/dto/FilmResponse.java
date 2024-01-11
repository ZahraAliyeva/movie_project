package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("english_name")
    private String englishName;

    @JsonProperty("original_name")
    private String originalName;

    @JsonProperty("alternative_titles")
    private List<String> alternativeTitles;

    @JsonProperty("original_language")
    private String originalLanguages;

    @JsonProperty("plot")
    private String plot;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("running_time")
    private Integer runningTime;

    @JsonProperty("adding_watchlist")
    private Boolean addingWatchlist;

    @JsonProperty("spoken_languages")
    private List<String> spokenLanguages;

    @JsonProperty("posters")
    private List<PosterResponse> posterResponse;

    @JsonProperty("number_of_watched")
    private Long numOfWatched;

    @JsonProperty("number_of_like")
    private Long numOfLike;

    @JsonProperty("releases")
    private Set<ReleaseResponse> releaseResponse;

    @JsonProperty("release_year")
    private Integer releaseYear;

    @JsonProperty("film_cast_and_crew")
    private Set<FilmCastAndCrewResponse> filmCastAndCrewResponses;

    @JsonProperty("studio_ids")
    private Set<Long> studioIds;

    @JsonProperty("country_ids")
    private Set<Long> countryIds;

    @JsonProperty("genre_ids")
    private Set<Long> genresIds;

    @JsonProperty("theme_ids")
    private Set<Long> themeIds;

    @JsonProperty("tmdb_link")
    private String tmdbLink;

    @JsonProperty("imdb_link")
    private String imdbLink;

    @JsonProperty("trailer")
    private String trailer;
}