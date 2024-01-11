package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastAndCrewFilmRequest {

    @JsonProperty("film_id")
    private Long film;

    @JsonProperty("occupation_id")
    private Long occupations;

    @JsonProperty("character_name")
    private String characterName;
}