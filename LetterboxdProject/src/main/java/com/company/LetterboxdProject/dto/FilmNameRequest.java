package com.company.LetterboxdProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmNameRequest {

    @JsonProperty("film_name")
    private String filmName;

    @JsonProperty("original_name")
    private Boolean originalName;

    @JsonProperty("language")
    private Long language;
}