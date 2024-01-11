package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmCastAndCrewResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cast_and_crew_id")
    private Long castAndCrew;

    @JsonProperty("occupation_id")
    private Long occupations;

    @JsonProperty("character_name")
    private String characterName;
}