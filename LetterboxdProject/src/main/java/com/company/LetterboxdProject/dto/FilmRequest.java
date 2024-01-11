package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmRequest {

    @JsonProperty("film_names")
    private Set<FilmNameRequest> filmNameRequest;

    @JsonProperty("posters")
    private Set<PosterRequest> posterRequests;

    @JsonProperty("releases")
    private Set<ReleaseRequest> releaseRequests;

    @JsonProperty("languages")
    private Set<FilmLanguageRequest> languageRequests;

    @JsonProperty("plot")
    private String plot;

    @JsonProperty("running_time")
    private Integer runningTime;

    @JsonProperty("film_cast_and_crew")
    private Set<FilmCastAndCrewRequest> filmCastAndCrewRequests;

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