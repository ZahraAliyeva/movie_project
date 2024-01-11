package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastAndCrewResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("cast_and_crew_photo_url")
    private String castAndCrewPhotoUrl;

    @JsonProperty("cast_and_crew_tmdb_link")
    private String castAndCrewTmdbLink;
}